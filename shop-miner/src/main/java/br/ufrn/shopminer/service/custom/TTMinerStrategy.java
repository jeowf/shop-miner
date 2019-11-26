package br.ufrn.shopminer.service.custom;

import br.ufrn.shopminer.framework.model.Config;
import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.framework.model.Tag;
import br.ufrn.shopminer.framework.service.WebScrapingService;
import br.ufrn.shopminer.framework.service.core.SearchStrategy;
import br.ufrn.shopminer.model.Favorite;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
//import jdk.javadoc.internal.doclets.formats.html.markup.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

@Component
public class TTMinerStrategy implements SearchStrategy {

	private class Pair<T> implements Comparable<Pair<T>>{
		public int time;
		public int itTime;
		public T value;
		
		public Pair(int t, int i, T v) {
			time = t;
			itTime = i;
			value = v;
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

		Tag tag = new Tag();
		Config config = new Config();
		Site site = new Site();

		tag.setClass_name("trend-card");
		tag.setName("Trending Topics List");
		tag.setId(0);

		site.setId(0);
		site.setName("trends24");
		site.setUrl("https://trends24.in/");
		site.setTags(new ArrayList<Tag>(Collections.singleton(tag)));
		site.setConfig(config);

		config.setSites(new ArrayList<Site>(Collections.singleton(site)));

		Favorite favorite = new Favorite(0, config, "tt", 10);;

		tasks.add( new Pair<Favorite>(3, 3, favorite) );
		/*

		tasks.add( new Pair<Favorite>(3, 3, new Favorite(0, null, "rx 550", 10)) );
		tasks.add( new Pair<Favorite>(10, 10, new Favorite(0, null, "gtx 1060", 20)) );
		tasks.add( new Pair<Favorite>(20, 20, new Favorite(0, null, "gt 1030", 30)) );
		tasks.add( new Pair<Favorite>(12, 12, new Favorite(0, null, "gtx 1080", 12)) );
		tasks.add( new Pair<Favorite>(16, 16, new Favorite(0, null, "quadro k620", 15)) );*/

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

	//@Bean
	//public TTMinerStrategy TTMinerStrategy(){
	//	return new TTMinerStrategy();
	//}

}
