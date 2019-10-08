package br.ufrn.shopminer.service;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.strategies.LinearRegression;
import br.ufrn.shopminer.strategies.PolynomialMonthRegression;
import br.ufrn.shopminer.strategies.Strategy;

import java.util.Collection;
import java.util.Date;

public class PredictorService {
    private Strategy strategy;

    public PredictorService() {
        this.strategy = new PolynomialMonthRegression();
    }
    public double predict(Date date) {
        return strategy.predict(date);
    }

    public void setStrategy(String strategy) {

        if (strategy.toLowerCase().equals("polynomial month regression")) {
            this.strategy = new PolynomialMonthRegression();
        }
        else {
            throw new IllegalArgumentException("O método " + strategy + " não foi implementado ainda");
        }
    }
}
