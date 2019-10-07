package br.ufrn.shopminer;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.strategies.PolynomialRegression;

import java.util.ArrayList;
import java.util.Date;

public class PredictorTest {
    public static int id = 0;
    public static String[] priceTags = {
            "R$ 1699,99",
            "R$ 1399,99",
            "R$ 1299,99"};
    public static String[] dates = {
            "2019/1/07",
            "2019/11/07",
            "2019/12/07",
    };
    public static Price getInst(int i){
        Price p = new Price();
        p.setDate(new Date(dates[i]));
        p.setValue(priceTags[i]);
        p.setId(id++);
        return p;
    }

    public static void main(String[] args) {
        ArrayList<Price> prices = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Price price = getInst(i);
            System.out.println("Mês: " + price.getDate().getMonth());
            prices.add(getInst(i));
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("Mês: " + prices.get(i).getDate().getMonth());
        }
        PolynomialRegression regression = new PolynomialRegression(prices, 1);
        Date date = prices.get(2).getDate();
        System.out.println(date.getMonth());
        System.out.println(regression.predict(date));
    }
}
