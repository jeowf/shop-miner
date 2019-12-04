package br.ufrn.minerin.cripto.service.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.ufrn.minerin.framework.model.Config;
import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.model.Tag;
import br.ufrn.minerin.framework.service.WebScrapingService;
import br.ufrn.minerin.framework.service.core.SearchStrategy;
import br.ufrn.minerin.model.Favorite;

@Component
@Primary
public class CriptoSearchStrategy implements SearchStrategy {


	private class Pair<T> implements Comparable<Pair<T>>{
		public int time;
		public int itTime;
		public T value;

		public Pair(int time, int itTime, T value) {
			this.time = time;
			this.itTime = itTime;
			this.value = value;
		}

		@Override
		public int compareTo(Pair<T> o) {
			if (time == o.time)
				return 0;
			else if (time > o.time)
				return 1;
			else
				return -1;
		}

	}

	private int currentTime;

	private PriorityQueue<Pair<Favorite>> tasks;

	boolean started = false;

	public void addTask(Favorite favorite) {
		Pair<Favorite> f = new Pair<Favorite>(currentTime + favorite.getRateInteger(), favorite.getRateInteger(), favorite);
		tasks.add(f);
	}
	
	private void init() {

		tasks = new PriorityQueue<Pair<Favorite>>();

		String[] locations = {"worldwide", "brazil", "brazil/sao-paulo", "brazil/fortaleza", "brazil/rio-de-janeiro", "brazil/recife"};

		for ( String location: locations ) {

			Tag tag = new Tag();
			Config config = new Config();
			Site site = new Site();

			tag.setClass_name("trend-card");
			tag.setName(location);
			tag.setId(0);

			site.setId(0);
			site.setName("investing");
			site.setUrl("https://br.investing.com/crypto/" + (location.equals("worldwide") ? "" : location));
			site.setTags(new ArrayList<Tag>(Collections.singleton(tag)));
			site.setConfig(config);

			config.setSites(new ArrayList<Site>(Collections.singleton(site)));

			Favorite favorite = new Favorite(0, config, "tt", 10);;

			tasks.add( new Pair<Favorite>(3, 3600, favorite) );
		}

	}

	private void executeCurrentQueries(WebScrapingService ws) {

		System.out.println("Current time: " + currentTime + "s");

		while (!tasks.isEmpty() && tasks.peek().time <= currentTime) {

			Pair<Favorite> p = tasks.poll();
			System.out.println("Pesquisando...");
			System.out.println(p.value.getValue());


			try {
				ws.search(p.value.getConfig(), p.value.getValue());

				System.out.println("cadastrou");

			} catch (IOException e) {
				System.out.println("error on search");
			}

			Pair<Favorite> newP = new Pair<Favorite>((currentTime + p.itTime), p.itTime, p.value);

			tasks.add(newP);

		}

	}

	@Override
	public void executeSearch(WebScrapingService ws) {

		if (!started) {
			init();
			started = true;
		}

		executeCurrentQueries(ws);

		currentTime++;

	}
	

}
