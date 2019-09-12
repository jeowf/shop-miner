package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Favorite;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.FavoriteService;
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

	@GetMapping("/config")
	@ApiOperation(value = "Returns a list of Configs")
	public ResponseEntity<List<Favorite>> getConfigs() {
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

	@GetMapping("/config/{id}")
	@ApiOperation(value = "Returns a Config by id")
	public ResponseEntity<Favorite> getConfig(@PathVariable("id") Integer id) {
		Favorite favorite;
		ResponseEntity<Favorite> re;
		
		try {
			favorite = favoriteService.findOne(id).get();
			re = new ResponseEntity<> (favorite, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/config")
	@ApiOperation(value = "Saves a new Config")
	public ResponseEntity<Favorite> postConfig(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;
		
		re = new ResponseEntity<>(null, HttpStatus.OK);
		
		return re;
	}

	@DeleteMapping("/config")
	@ApiOperation(value = "Deletes a Config")
	public ResponseEntity<Favorite> deleteConfig(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;

		try {
			favoriteService.delete(favorite);
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;

	}
	@PutMapping
	@ApiOperation(value = "Updates a Config")
	public ResponseEntity<Favorite> putConfig(@RequestBody Favorite favorite){
		ResponseEntity<Favorite> re;

		try {
			favoriteService.save(favorite);
			re = new ResponseEntity<> (favorite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;
	}
}
