package br.ufrn.shopminer.framework.spec;

import br.ufrn.shopminer.framework.service.core.PersistStrategy;
import br.ufrn.shopminer.framework.service.core.QueryFactory;
import br.ufrn.shopminer.framework.service.core.SearchStrategy;
import br.ufrn.shopminer.service.custom.ProductPersistStrategy;
import br.ufrn.shopminer.service.custom.ShopMinerStrategy;
import br.ufrn.shopminer.service.custom.SiteProductPriceFactory;

public class MinerinConfig { 

    private static MinerinConfig single_instance = null; 
  

    private SearchStrategy searchStrategy;
    private PersistStrategy persistStrategy;
    private QueryFactory queryFactory;
  
    // private constructor restricted to this class itself 
    private MinerinConfig() 
    { 
        searchStrategy = new ShopMinerStrategy();
        persistStrategy = new ProductPersistStrategy();
        queryFactory = new SiteProductPriceFactory();
    } 
  
    // static method to create instance of MinerinConfig class 
    public static MinerinConfig getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new MinerinConfig(); 
  
        return single_instance; 
    } 
    
    public SearchStrategy getSearchStrategy() {
    	return searchStrategy;
    }
    
    public PersistStrategy getPersistStrategy() {
    	return persistStrategy;
    }

	public QueryFactory getQueryFactory() {
		return queryFactory;
	}

    
    
}