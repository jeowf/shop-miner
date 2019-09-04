package br.ufrn.shopminer.controller;

import br.ufrn.shopminer.model.Config;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
//import br.ufrn.shopminer.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConfigController {
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping("/config/{id}")
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
	public ResponseEntity<Config> postSite(@RequestBody Config config){
		ResponseEntity<Config> re;
		
		re = new ResponseEntity<>(null, HttpStatus.OK);
		
		return re;
	}

}
