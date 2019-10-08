package br.ufrn.shopminer.strategies;

import br.ufrn.shopminer.model.Price;

import java.util.ArrayList;
import java.util.Date;

public interface Strategy {
    public double predict(Date date);
    public void train(ArrayList<Price> prices, int degree);
}
