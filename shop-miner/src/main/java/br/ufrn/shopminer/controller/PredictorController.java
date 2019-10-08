package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.*;
import br.ufrn.shopminer.service.FavoriteService;
import br.ufrn.shopminer.service.PredictorService;
import br.ufrn.shopminer.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class PredictorController {
    @Autowired
    private PredictorService predictorService;
    private FavoriteService favoriteService;
    private ProductService productService;

    @GetMapping("/{fav}/{date}") //
    @ApiOperation(value = "Returns an object with best price")
    public ResponseEntity<Double> getPredictions(
            @PathVariable("id") Integer fav,
            @PathVariable("initial-date") String initDate,
            @PathVariable("final-date") String finalDate) {
        ResponseEntity<Double> re;

        try {
            Favorite favorite = favoriteService.findOne(fav).get();
            List<Site> sites = favorite.getConfig().getSites();
            List<Price> prices =  favoriteService.findPrices(sites.get(0).getId(),fav);
            Date start = new Date(initDate);
            predictorService.train(prices, 5);
            double best = predictorService.predict(start);

            //SiteProductPrice best = new SiteProductPrice();
            //Price bestPrice = new Price();
            //Favorite favorite = favoriteService.findOne(fav).get();
            //String product_name = favorite.getValue();
            //Product product =  productService.findByName(product_name);
            //List<Site> sites = favorite.getConfig().getSites();
            //Date start = new Date(initDate);
            //Date end = new Date((finalDate));
            //double min = Double.parseDouble(
            //        favoriteService.findPrices(sites.get(0).getId(),fav).get(0).getValue()
            //        .replaceAll("R|\\$| |\\.", "").replaceAll(",","."));
            //bestPrice.setValue("R$ " + Double.toString(min).replaceAll(".",","));
            //best.setPrice(bestPrice);
            //for (Site site: sites) {
            //    List<Price> prices =  favoriteService.findPrices(sites.get(0).getId(),fav);
            //    for (Price price: prices) {
            //        double p = Double.parseDouble( price.getValue()
            //                .replaceAll("R|\\$| |\\.", "").replaceAll(",","."));
            //        if (p < min ) {
            //            min = p;
            //        }
            //    }
            //}

            //List<Price> prices =  favoriteService.findPrices(sites.get(0).getId(),fav);

            re = new ResponseEntity<Double> (best, HttpStatus.OK);
        } catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return re;
    }

}
