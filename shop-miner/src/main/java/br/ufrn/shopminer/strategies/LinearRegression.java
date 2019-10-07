package br.ufrn.shopminer.strategies;

import br.ufrn.shopminer.model.Price;

import java.util.Collection;
import java.util.Date;

public class LinearRegression implements Strategy{
    private boolean trained = false;
    public LinearRegression(Collection<Price> prices) {
        for (Price price :
             prices ) {
            int day = price.getDate().getDay();
            int month = price.getDate().getMonth();
            int year = price.getDate().getYear();
            double value = Double.parseDouble(price.getValue()
                    .replace(".", "")
                    .replace(",","."));
        }
    }
    @Override
    public double predict(Date date) {
        // @TODO
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        return -1;
    }
}

