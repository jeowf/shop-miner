package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import br.ufrn.shopminer.model.Site;
//import br.ufrn.shopminer.service.SiteService;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Trending Topics")
@CrossOrigin(origins="*")
public class TTController {
	
	@Autowired
	private PriceService priceService;

	@GetMapping("/tts")
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

	@GetMapping("/tts/{id}")
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
	
	@PostMapping("/tts")
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

	@DeleteMapping("/tts")
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
	@PutMapping("/tts")
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
