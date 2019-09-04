package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.Config;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
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
public class ConfigController {
	
	@Autowired
	private ConfigService configService;

	@GetMapping("/config")
	@ApiOperation(value = "Retorna os dados do Site")
	public ResponseEntity<List<Config>> getSites() {
		List<Config> sites;
		ResponseEntity<List<Config>> re;

		try {
			sites = configService.findAll();
			re = new ResponseEntity<> (sites, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/config/{id}")
	@ApiOperation(value = "Retorna os dados do Site")
	public ResponseEntity<Config> getConfig(@PathVariable("id") Integer id) {
		Config config;
		ResponseEntity<Config> re;
		
		try {
			config = configService.findOne(id).get();
			re = new ResponseEntity<> (config, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/config")
	@ApiOperation(value = "Retorna os dados do Site")
	public ResponseEntity<Config> postConfig(@RequestBody Config config){
		ResponseEntity<Config> re;
		
		re = new ResponseEntity<>(null, HttpStatus.OK);
		
		return re;
	}

	@DeleteMapping("/config")
	@ApiOperation(value = "Retorna os dados do Site")
	public ResponseEntity<Config> deleteConfig(@RequestBody Config config){
		ResponseEntity<Config> re;

		try {
			configService.delete(config);
			re = new ResponseEntity<> (config, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;

	}
	@PutMapp
	public ResponseEntity<Config> putConfig(@RequestBody Config config){
		ResponseEntity<Config> re;

		try {
			configService.save(config);
			re = new ResponseEntity<> (config, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;
	}
}
