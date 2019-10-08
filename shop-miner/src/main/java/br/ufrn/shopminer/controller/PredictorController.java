package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.*;
import br.ufrn.shopminer.service.FavoriteService;
import br.ufrn.shopminer.service.PredictorService;
import br.ufrn.shopminer.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.netlib.util.doubleW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class PredictorController {
    @Autowired
    private PredictorService predictorService;
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/predict/{fav}/{mi}/{yi}/{me}/{ye}") //
    @ApiOperation(value = "Returns an object with best price")
    public ResponseEntity<Double> getPredictions(
            @PathVariable("fav") Integer fav,
            @PathVariable("mi") Integer mi,
            @PathVariable("yi") Integer yi,
            @PathVariable("me") Integer me,
            @PathVariable("ye") Integer ye
            ) {
        ResponseEntity<Double> re;

        double best = 999999;
        Date bestDate = new Date();
        Site bestSite = new Site();
        	
        try {
            Favorite favorite = favoriteService.findOne(fav).get();
            List<Site> sites = favorite.getConfig().getSites();
            
          
            Date start = new Date();
            start.setMonth(2);
            start.setYear(yi);

            
            Date end = new Date();
            start.setMonth(9);
            start.setYear(ye);
            
            
            
            for (Site site : sites) {
            	List<Price> prices =  favoriteService.findPrices(fav, site.getId());
            	predictorService.train(prices, 5);
            	
            	Date aux = new Date();
                start.setMonth(start.getMonth());
                start.setYear(start.getYear());
                
                System.out.println(start);
                System.out.println(end);
                
                while (aux.before(end)) {
                	System.out.println("bef " + aux);
                	double pred = predictorService.predict(start);
                	
                	if (pred < best) {
                		best = pred;
                		bestDate = new Date();
                		bestDate.setMonth(aux.getMonth());
                		bestDate.setYear(aux.getYear());
                		bestSite = site;
                	}
                	
                	aux.setMonth(aux.getMonth()+1);
                }
            	
                
            }
            
            
            System.out.println(best);
            System.out.println(bestDate);
            System.out.println(bestSite.getName());
            
            
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
