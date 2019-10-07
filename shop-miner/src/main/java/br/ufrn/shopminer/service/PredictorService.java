package br.ufrn.shopminer.service;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.strategies.LinearRegression;
import br.ufrn.shopminer.strategies.Strategy;

import java.util.Collection;

public class PredictorService {
    private Strategy strategy;

    //public Collection<Price> predict(Collection<Price> prices) {
    //    return strategy.predict();
    //}

    public void setStrategy(String strategy) {

        //if (strategy.toLowerCase().equals("linear regression")) {
        //    this.strategy = new LinearRegression();
        //}
        //else if (strategy.toLowerCase().equals("")) {

        //}
    }
}
