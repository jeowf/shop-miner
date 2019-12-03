package br.ufrn.minerin.shopminer;

import java.io.IOException;
import java.util.PriorityQueue;

import br.ufrn.minerin.framework.service.WebScrapingService;
import br.ufrn.minerin.framework.service.core.SearchStrategy;
import br.ufrn.minerin.model.Favorite;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class ShopMinerStrategy implements SearchStrategy {

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
			//System.out.println(p.value.getValue());
			

			
			
			try {
				ws.search(p.value.getConfig(), p.value.getValue());
				
				System.out.println("cadastrou");
				
			} catch (IOException e) {
				System.out.println("error");
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
