package br.ufrn.shopminer.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.repository.SiteRepository;

@RestController
@RequestMapping(value="/api")
public class SiteResource {

	@Autowired
	SiteRepository siteRepository;

	@GetMapping("/sites")
	public List<Site> listSites(){
		return siteRepository.findAll();
	}

	@GetMapping("/site/{id}")
	public Site getSite(@PathVariable(value="id") Integer id){
		return siteRepository.findById(id.intValue());
	}

	@PostMapping("/site")
	public Site postSite(@RequestBody Site site) {
		return siteRepository.save(site);
	}
	
}
