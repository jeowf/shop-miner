package br.ufrn.shopminer.framework.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.netlib.util.booleanW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.shopminer.framework.service.core.SearchStrategy;
import br.ufrn.shopminer.framework.spec.MinerinConfig;
import br.ufrn.shopminer.model.Favorite;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

	
	@Autowired
	private WebScrapingService ws;
	
	private SearchStrategy ss;
		

	@Transactional(readOnly = false)
	@Scheduled(fixedDelay = 1000)
	public void executeSearch() {
		if (ss == null)
			ss = MinerinConfig.getInstance().getSearchStrategy();
		
		ss.executeSearch(ws);

	}
	
	
}
