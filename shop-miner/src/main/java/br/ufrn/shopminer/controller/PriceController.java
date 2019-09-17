package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.Price;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import br.ufrn.shopminer.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class PriceController {
	
	@Autowired
	private PriceService priceService;

	@GetMapping("/price")
	@ApiOperation(value = "Returns a list of Price")
	public ResponseEntity<List<Price>> getPrices() {
		List<Price> configs;
		ResponseEntity<List<Price>> re;

		try {
			configs = priceService.findAll();
			re = new ResponseEntity<> (configs, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/price/{id}")
	@ApiOperation(value = "Returns a Price by id")
	public ResponseEntity<Price> getPrice(@PathVariable("id") Integer id) {
		Price price;
		ResponseEntity<Price> re;
		
		try {
			price = priceService.findOne(id).get();
			re = new ResponseEntity<> (price, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/price")
	@ApiOperation(value = "Saves a new Price")
	public ResponseEntity<Price> postPrice(@RequestBody Price price){
		ResponseEntity<Price> re;
		
		try {
			priceService.save(price);
			re = new ResponseEntity<> (price, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

	@DeleteMapping("/price")
	@ApiOperation(value = "Deletes a Price")
	public ResponseEntity<Price> deletePrice(@RequestBody Price price){
		ResponseEntity<Price> re;

		try {
			priceService.delete(price);
			re = new ResponseEntity<> (price, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;

	}
	@PutMapping("/price")
	@ApiOperation(value = "Updates a Price")
	public ResponseEntity<Price> putPrice(@RequestBody Price price){
		ResponseEntity<Price> re;

		try {
			priceService.save(price);
			re = new ResponseEntity<> (price, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;
	}
}
