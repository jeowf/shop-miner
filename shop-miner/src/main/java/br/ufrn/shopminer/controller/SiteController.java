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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.SiteService;

@RestController
@RequestMapping("/api")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ConfigService configService;
	
//	@GetMapping
//	public String index(Model model) {
//		List<Site> all = siteService.findAll();
//		model.addAttribute("listSite", all);
//		model.addAttribute("");
//		return "site/index";
//	}
	
	@GetMapping("/site/{id}")
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
	public ResponseEntity<Site> postSite(@RequestBody Site site){
		ResponseEntity<Site> re;
		
		re = new ResponseEntity<>(null, HttpStatus.OK);
		
		return re;
	}
	
	
	
	
	
	
	
	

}
