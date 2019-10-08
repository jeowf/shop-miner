package br.ufrn.shopminer.strategies;

import br.ufrn.shopminer.model.Price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface Strategy {
    public double predict(Date date);
    public void train(List<Price> prices, int degree);
}
