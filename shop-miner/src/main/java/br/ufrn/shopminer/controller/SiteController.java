package br.ufrn.shopminer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.SiteService;

@RestController
@RequestMapping("/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping
	public String index(Model model) {
		List<Site> all = siteService.findAll();
		model.addAttribute("listSite", all);
		model.addAttribute("");
		return "site/index";
	}
	
	@GetMapping("/{id}")
	public Site show(Model model, @PathVariable("id") Integer id) {
		//if (id != null) {
			Site site = siteService.findById(id);
			model.addAttribute("site", site);
		//}
		return site;
	}
	
	
	

}
