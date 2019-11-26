package br.ufrn.minerin.framework.strategies;

import br.ufrn.minerin.shopminer.model.Price;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomStrategy  implements Strategy {
    private int lower;
    private int upper;
    public RandomStrategy(int lower, int upper){
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public double predict(Date date) {
        Random gen = new Random( lower - upper);
        return gen.nextInt();
    }

    @Override
    public void train(List<Price> prices, int degree) {
        return;
    }
}
