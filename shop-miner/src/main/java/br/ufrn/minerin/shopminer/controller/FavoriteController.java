package br.ufrn.minerin.shopminer.controller;

import br.ufrn.minerin.framework.service.SiteService;
import br.ufrn.minerin.model.Favorite;
import br.ufrn.minerin.shopminer.model.Price;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.minerin.shopminer.service.FavoriteService;
import br.ufrn.minerin.shopminer.service.ProductService;
import br.ufrn.minerin.shopminer.service.SiteProductPriceService;
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
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SiteProductPriceService sppService;

	@GetMapping("/favorite")
	@ApiOperation(value = "Returns a list of Configs")
	public ResponseEntity<List<Favorite>> getFavorites() {
		List<Favorite> configs;
		ResponseEntity<List<Favorite>> re;

		try {
			configs = favoriteService.findAll();
			re = new ResponseEntity<> (configs, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/favorite/{id}")
	@ApiOperation(value = "Returns a Config by id")
	public ResponseEntity<Favorite> getFavorite(@PathVariable("id") Integer id) {
		Favorite favorite;
		ResponseEntity<Favorite> re;
		
		try {
			favorite = favoriteService.findOne(id).get();
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/favorite")
	@ApiOperation(value = "Saves a new Config")
	public ResponseEntity<Favorite> postFavorite(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;
		
		try {
			favoriteService.save(favorite);
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

	@DeleteMapping("/favorite")
	@ApiOperation(value = "Deletes a Config")
	public ResponseEntity<Favorite> deleteFavorite(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;

		try {
			favoriteService.delete(favorite);
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;

	}
	
	@PutMapping("/favorite")
	@ApiOperation(value = "Updates a Favorite")
	public ResponseEntity<Favorite> putFavorite(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;

		try {
			favoriteService.save(favorite);
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;
	}
	
	@GetMapping("/favorite/{id}/{site}")
	@ApiOperation(value = "desc")
	public ResponseEntity<List<Price>> getPrices(@PathVariable("id") Integer id, @PathVariable("site") Integer id_site) {

		List<Price> prices;
		
		ResponseEntity<List<Price>> re;
		try {
			prices = favoriteService.findPrices(id, id_site);
			
			
			re = new ResponseEntity<> (prices, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}
}
