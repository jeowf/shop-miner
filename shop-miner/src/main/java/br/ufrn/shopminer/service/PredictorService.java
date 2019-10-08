package br.ufrn.shopminer.service;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.strategies.PolynomialMonthRegression;
import br.ufrn.shopminer.strategies.Strategy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class PredictorService {
    private Strategy strategy;

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
}
