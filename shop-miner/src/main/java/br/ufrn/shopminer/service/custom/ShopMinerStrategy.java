package br.ufrn.shopminer.service.custom;

import java.io.IOException;
import java.util.PriorityQueue;

import br.ufrn.shopminer.framework.service.ScheduleService;
import br.ufrn.shopminer.framework.service.WebScrapingService;
import br.ufrn.shopminer.framework.service.core.SearchStrategy;
import br.ufrn.shopminer.model.Favorite;

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

	}
	
	private void executeCurrentQueries(WebScrapingService ws) {

		
		while (!tasks.isEmpty() && tasks.peek().time <= currentTime) {

			Pair<Favorite> p = tasks.poll();
			System.out.println(p.value.getValue());
			
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
