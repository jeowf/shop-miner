package br.ufrn.minerin;

import br.ufrn.minerin.shopminer.model.Price;
import br.ufrn.minerin.framework.strategies.PolynomialMonthRegression;

import java.util.ArrayList;
import java.util.Date;

public class PredictorTest {
    public static int id = 0;
    public static String[] priceTags = {
            "R$ 1699,99",
            "R$ 1399,99",
            "R$ 1299,99",
            "R$ 1459,99",
            "R$ 1692,99"};
    public static String[] dates = {
            "2019/01/07",
            "2019/02/07",
            "2019/03/07",
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
        for (int i = 0; i < 5; i++) {
            Price price = getInst(i);
            System.out.println("Mês: " + price.getDate().getMonth());
            prices.add(getInst(i));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Mês: " + prices.get(i).getDate().getMonth());
        }
        PolynomialMonthRegression regression = new PolynomialMonthRegression(prices, 5);
        Date date = prices.get(2).getDate();
        System.out.println(date.getMonth());
        System.out.println(regression.predict(date));
        System.out.println("Coeficiente de Determinação:" + regression.R2());
    }
}
