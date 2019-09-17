package br.ufrn.shopminer.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.model.SiteProductPrice;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.ProductService;
import br.ufrn.shopminer.service.SiteProductPriceService;
import br.ufrn.shopminer.service.SiteService;
import br.ufrn.shopminer.service.WebScrapingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class WebScrapingController {
	
	
	@Autowired
	private WebScrapingService wsService;
	
	@Autowired
	private ConfigService configService;
	
	//@Autowired
	//private ProductService productService;
	
	@GetMapping("/search/{config}/{query}")
	@ApiOperation(value = "Returns a list of Product")
	public ResponseEntity<List<SiteProductPrice>> search(@PathVariable("config") Integer configId, 
												@PathVariable("query") String query){
		
		List<SiteProductPrice> products;
		ResponseEntity<List<SiteProductPrice>> re;

		try {
			products = wsService.search(configService.findOne(configId).get(), query);
			re = new ResponseEntity<> (products, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/search/auto/{config}")
	@ApiOperation(value = "Returns a list of Product")
	public ResponseEntity<Object> auto(@PathVariable("config") Integer configId) {
		ResponseEntity<Object> re;
			
		try {
			wsService.autoSearch(configService.findOne(configId).get());
			re =  new ResponseEntity<> (null, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
		
		return re;
	}
	

}
