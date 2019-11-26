package br.ufrn.minerin.framework.controller;

import br.ufrn.minerin.framework.model.Config;
import br.ufrn.minerin.framework.service.ConfigService;
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
	@ApiOperation(value = "Returns a list of Configs")
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
	@ApiOperation(value = "Returns a Config by id")
	public ResponseEntity<Config> getConfig(@PathVariable("id") Integer id) {
		Config config;
		ResponseEntity<Config> re;
		
		try {
			config = configService.findOne(id).get();
			re = new ResponseEntity<> (config, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/config")
	@ApiOperation(value = "Saves a new Config")
	public ResponseEntity<Config> postConfig(@RequestBody Config config){
		ResponseEntity<Config> re;
		
		try {
			configService.save(config);
			re = new ResponseEntity<> (config, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

	@DeleteMapping("/config")
	@ApiOperation(value = "Deletes a Config")
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
	@PutMapping("/config")
	@ApiOperation(value = "Updates a Config")
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
