package br.ufrn.shopminer.service;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

	private class Pair implements Comparable<Pair>{
		public int time;
		public int itTime;
		public String value;
		
		public Pair(int t, int i, String v) {
			time = t;
			itTime = i;
			value = v;
		}


		@Override
		public int compareTo(Pair o) {
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
	private PriorityQueue<Pair> tasks;

	private boolean started = false; 
	
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
		
		tasks = new PriorityQueue<ScheduleService.Pair>();
		
		tasks.add(new Pair(3, 3, "[1]"));
		tasks.add(new Pair(5, 5, "[2]"));
		tasks.add(new Pair(3, 3, "[3]"));
		tasks.add(new Pair(12, 12, "[4]"));

	}
	
	private void executeCurrentQueries() {
		
		//System.out.println(currentTime);
		
		
		while (!tasks.isEmpty() && tasks.peek().time <= currentTime) {
			Pair p = tasks.poll();
			//System.out.println(p.value);
			
			Pair newP = new Pair((currentTime + p.itTime), p.itTime, p.value);
			
			tasks.add(newP);
			
		}
		//System.out.println(tasks.peek().value);
		
		
		
	}
	
	
	
	
	
}
