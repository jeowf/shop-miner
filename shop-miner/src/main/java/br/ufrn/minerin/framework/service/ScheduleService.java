package br.ufrn.minerin.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.minerin.framework.service.core.SearchStrategy;
import br.ufrn.minerin.framework.spec.MinerinConfig;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

	
	@Autowired
	private WebScrapingService ws;

	@Autowired
	private SearchStrategy ss;
		

	@Transactional(readOnly = false)
	@Scheduled(fixedDelay = 1000)
	public void executeSearch() {
		if (ss == null)
			ss = MinerinConfig.getInstance().getSearchStrategy();
		
		ss.executeSearch(ws);

	}
	
	
}
