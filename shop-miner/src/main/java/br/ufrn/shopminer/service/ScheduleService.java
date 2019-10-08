package br.ufrn.shopminer.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.netlib.util.booleanW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.shopminer.model.Favorite;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

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
	
	// tirar o *60 na pratica
	
	//public static final int TIME_LIMIT = 43200;
	public static final int TIME_LIMIT = 20;
	
	private int currentTime;
	
	private PriorityQueue<Pair<Favorite>> tasks;
	
	boolean started = false;
	
	@Autowired
	private WebScrapingService ws;
		
	//private boolean started = false; 
	
	@Transactional(readOnly = false)
	@Scheduled(fixedDelay = 1000)
	public void test() {
		
		if (!started) {
			startTest();
			started = true;
		}
		
		executeCurrentQueries();
		
		currentTime++;
		//currentTime %= TIME_LIMIT;
	}
	
	
	private void startTest() {
		
		tasks = new PriorityQueue<ScheduleService.Pair<Favorite>>();
		/*
		tasks.add(new Pair(3, 3, "[1]"));
		tasks.add(new Pair(5, 5, "[2]"));
		tasks.add(new Pair(3, 3, "[3]"));
		tasks.add(new Pair(12, 12, "[4]"));
*/
	}
	
	@Transactional(readOnly = false)
	public void addTask(Favorite favorite) {
		Pair<Favorite> f = new Pair<Favorite>(currentTime + favorite.getRateInteger(), favorite.getRateInteger(), favorite);
		tasks.add(f);
	}
	
	
	@Transactional(readOnly = false)
	private void executeCurrentQueries() {
		
		//System.out.println(currentTime);
		
		
		while (!tasks.isEmpty() && tasks.peek().time <= currentTime) {

			Pair<Favorite> p = tasks.poll();
			System.out.println(p.value.getValue());
			
			try {
				ws.search(p.value.getConfig(), p.value.getValue());
				System.out.println("cadastrou");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("deu nao :/");
			}
			
			Pair<Favorite> newP = new Pair<Favorite>((currentTime + p.itTime), p.itTime, p.value);
			
			tasks.add(newP);
			
			
			
		}
		//System.out.println(tasks.peek().value);
		
		
		
	}
	
	
	
	
	
}
