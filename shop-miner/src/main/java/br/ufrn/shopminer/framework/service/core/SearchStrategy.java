package br.ufrn.shopminer.framework.service.core;

import br.ufrn.shopminer.framework.service.WebScrapingService;

public interface SearchStrategy {
	public void executeSearch(WebScrapingService ws);
}
