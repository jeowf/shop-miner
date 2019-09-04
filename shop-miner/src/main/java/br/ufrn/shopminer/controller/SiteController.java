package br.ufrn.shopminer.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	
	@GetMapping("/site")
	@ApiOperation(value = "Returns a list of Site")
	public ResponseEntity<List<Site>> getSites() {
		List<Site> sites;
		ResponseEntity<List<Site>> re;
		
		try {
			sites = siteService.findAll();
			re = new ResponseEntity<> (sites, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@GetMapping("/site/{id}")
	@ApiOperation(value = "Returns a Site by id")
	public ResponseEntity<Site> getSite(@PathVariable("id") Integer id) {
		Site site;
		ResponseEntity<Site> re;
		
		try {
			site = siteService.findOne(id).get();
			re = new ResponseEntity<> (site, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/site")
	@ApiOperation(value = "Saves a new Site")
	public ResponseEntity<Site> postSite(@RequestBody Site site){
		ResponseEntity<Site> re;
		
		//re = new ResponseEntity<>(null, HttpStatus.OK);
		
		try {
			siteService.save(site);
			re = new ResponseEntity<> (site, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}
	
	//@PutMapping("/site")
	@DeleteMapping("/site")
	@ApiOperation(value = "Deletes a Site")
	public ResponseEntity<Site> deleteSite(@RequestBody Site site){
		ResponseEntity<Site> re;
		
		try {
			siteService.delete(site);
			re = new ResponseEntity<> (site, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;	
		
	}
	
	@PutMapping("/site")
	@ApiOperation(value = "Updates a Site")
	public ResponseEntity<Site> putSite(@RequestBody Site site){
		ResponseEntity<Site> re;

		try {
			siteService.save(site);
			re = new ResponseEntity<> (site, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}
	
	
	
	
	
	
	

}
