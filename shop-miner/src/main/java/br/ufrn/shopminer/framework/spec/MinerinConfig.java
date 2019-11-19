package br.ufrn.shopminer.framework.spec;

import br.ufrn.shopminer.framework.service.core.SearchStrategy;
import br.ufrn.shopminer.service.custom.ShopMinerStrategy;

public class MinerinConfig { 

    private static MinerinConfig single_instance = null; 
  

    private SearchStrategy searchStrategy;
  
    // private constructor restricted to this class itself 
    private MinerinConfig() 
    { 
        searchStrategy = new ShopMinerStrategy();
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
}