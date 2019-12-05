package br.ufrn.minerin.cripto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.minerin.cripto.model.Coin;
import br.ufrn.minerin.cripto.service.CoinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class CriptoController  {
	
	@Autowired
	private CoinService priceService;

	@GetMapping("/coin")
	@ApiOperation(value = "Returns a list of Coin")
	public ResponseEntity<List<Coin>> getCoins() {
		List<Coin> coins;
		ResponseEntity<List<Coin>> re;

		try {
			coins = priceService.findAll();
			re = new ResponseEntity<> (coins, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}
	
	@GetMapping("/coin/{id}")
	@ApiOperation(value = "Returns a Coin by id")
	public ResponseEntity<Coin> getCoin(@PathVariable("id") Integer id) {
		Coin coin;
		ResponseEntity<Coin> re;
		
		try {
			coin = priceService.findOne(id).get();
			re = new ResponseEntity<> (coin, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}
	
}
