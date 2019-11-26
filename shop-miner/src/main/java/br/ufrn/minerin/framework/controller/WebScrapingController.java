package br.ufrn.minerin.framework.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.minerin.framework.service.ConfigService;
import br.ufrn.minerin.framework.service.WebScrapingService;
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
	public ResponseEntity<List<Serializable>> search(@PathVariable("config") Integer configId, 
												@PathVariable("query") String query){
		
		List<Serializable> products = null;
		ResponseEntity<List<Serializable>> re;

		try {
			products = (List<Serializable>) wsService.search(configService.findOne(configId).get(), query);
			re = new ResponseEntity<> (products, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	/*
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
	*/

}
