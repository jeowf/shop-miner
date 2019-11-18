package br.ufrn.shopminer.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.ExtendedSite;

import br.ufrn.shopminer.service.ExtendedSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API REST ExtendedSite")
@CrossOrigin(origins="*")
public class ExtendedSiteController {
	
	@Autowired
	private ExtendedSiteService esiteService;
	
	
	@GetMapping("/esite")
	@ApiOperation(value = "Returns a list of ExtendedSite")
	public ResponseEntity<List<ExtendedSite>> getExtendedSites() {
		List<ExtendedSite> esites;
		ResponseEntity<List<ExtendedSite>> re;
		
		try {
			esites = esiteService.findAll();
			re = new ResponseEntity<> (esites, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@GetMapping("/esite/{id}")
	@ApiOperation(value = "Returns a ExtendedSite by id")
	public ResponseEntity<ExtendedSite> getExtendedSite(@PathVariable("id") Integer id) {
		ExtendedSite esite;
		ResponseEntity<ExtendedSite> re;
		
		try {
			esite = esiteService.findOne(id).get();
			re = new ResponseEntity<> (esite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/esite")
	@ApiOperation(value = "Saves a new ExtendedSite")
	public ResponseEntity<ExtendedSite> postExtendedSite(@RequestBody ExtendedSite esite){
		ResponseEntity<ExtendedSite> re;
		
		//re = new ResponseEntity<>(null, HttpStatus.OK);
		
		try {
			esiteService.save(esite);
			re = new ResponseEntity<> (esite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}
	
	//@PutMapping("/esite")
	@DeleteMapping("/esite")
	@ApiOperation(value = "Deletes a ExtendedSite")
	public ResponseEntity<ExtendedSite> deleteExtendedSite(@RequestBody ExtendedSite esite){
		ResponseEntity<ExtendedSite> re;
		
		try {
			esiteService.delete(esite);
			re = new ResponseEntity<> (esite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;	
		
	}
	
	@PutMapping("/esite")
	@ApiOperation(value = "Updates a ExtendedSite")
	public ResponseEntity<ExtendedSite> putExtendedSite(@RequestBody ExtendedSite esite){
		ResponseEntity<ExtendedSite> re;

		try {
			esiteService.save(esite);
			re = new ResponseEntity<> (esite, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

}
