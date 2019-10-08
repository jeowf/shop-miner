package br.ufrn.shopminer.service;

import br.ufrn.shopminer.model.Favorite;
import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.strategies.PolynomialMonthRegression;
import br.ufrn.shopminer.strategies.Strategy;
import java_cup.internal_error;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class PredictorService {
    private Strategy strategy;
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private ProductService productService;

    public PredictorService() {
        this.strategy = new PolynomialMonthRegression();
    }
    
    @Transactional(readOnly = false)
    public double predict(Date date) {
        return strategy.predict(date);
    }

    @Transactional(readOnly = false)
    public void setStrategy(String strategy) {

        if (strategy.toLowerCase().equals("polynomial month regression")) {
            this.strategy = new PolynomialMonthRegression();
        }
        else {
            throw new IllegalArgumentException("O método " + strategy + " não foi implementado ainda");
        }
    }

    @Transactional(readOnly = false)
    public void train(List<Price> prices, int degree) {
        this.strategy.train(prices, degree);
    }
    
    @Transactional(readOnly = false)
    public String getPredictions(Integer fav, 
    		                     Integer di, Integer mi, Integer yi,
    		                     Integer de, Integer me, Integer ye) {
    	
    	double best = 999999;
        Date bestDate = new Date();
        Site bestSite = new Site();
        
        String result = "";
        
        Favorite favorite = favoriteService.findOne(fav).get();
        List<Site> sites = favorite.getConfig().getSites();
        
      
        Date start = new Date(yi, mi, di);            
        Date end = new Date(ye, me, de);

        for (Site site : sites) {
        	List<Price> prices =  favoriteService.findPrices(fav, site.getId());
        	train(prices, 5);
        	
        	Date aux = new Date();
            aux.setMonth(start.getMonth());
            aux.setYear(start.getYear());
            
            
            while (aux.before(end)) {
            	double pred = predict(aux);
            	
            	if (pred < best) {
            		best = pred;
            		bestDate = new Date();
            		bestDate.setMonth(aux.getMonth());
            		bestDate.setYear(aux.getYear());
            		bestSite = site;
            	}
            	
            	aux.setMonth(aux.getMonth()+1);
            }
        	
            
        }
        
        

        String dateString = bestDate.getDay() + "-" + (bestDate.getMonth()+1) + "-" + bestDate.getYear();
        result = best + "|" + dateString + "|" + bestSite.getName();
        //result = "O melhor dia para adiquirir o produto é em "+ dateString + " custando R$ " + best + " na loja "+ bestSite.getName();
        
        return result;
    	
    }
    
    
}
