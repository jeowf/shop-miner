package br.ufrn.minerin.framework.strategies;

import br.ufrn.minerin.shopminer.model.Price;

import java.util.Date;
import java.util.List;

public interface Strategy {
    public double predict(Date date);
    public void train(List<Price> prices, int degree);
}
