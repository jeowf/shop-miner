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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.SiteService;
import br.ufrn.shopminer.service.WebScrapingService;

@RestController
@RequestMapping("/api")
public class WebScrapingController {
	
	
	@Autowired
	private WebScrapingService wsService;
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping("/search/{config}/{query}")
	public ResponseEntity<List<Product>> search(@PathVariable("config") Integer configId, 
												@PathVariable("query") String query) throws IOException{
		
		List<Product> products;
		ResponseEntity<List<Product>> re;

		try {
			products = wsService.search(configService.findOne(configId).get(), query);
			re = new ResponseEntity<> (products, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}
	

}
