package br.ufrn.minerin.shopminer.controller;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.shopminer.service.FavoriteService;
import br.ufrn.minerin.shopminer.service.PredictorService;
import br.ufrn.minerin.shopminer.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @GetMapping("/predict/{fav}/{di}-{mi}-{yi}/{de}-{me}-{ye}") //
    @ApiOperation(value = "Returns an object with best price")
    public ResponseEntity<String> getPredictions(
            @PathVariable("fav") Integer fav,
            @PathVariable("di") Integer di,
            @PathVariable("mi") Integer mi,
            @PathVariable("yi") Integer yi,
            @PathVariable("de") Integer de,
            @PathVariable("me") Integer me,
            @PathVariable("ye") Integer ye
            ) {
        ResponseEntity<String> re;

        double best = 999999;
        Date bestDate = new Date();
        Site bestSite = new Site();
        
        String result = "";
        
        try {
            result = predictorService.getPredictions(fav, di, mi, yi, de, me, ye);
            re = new ResponseEntity<String> (result, HttpStatus.OK);
        } catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
        return re;
    }

}
