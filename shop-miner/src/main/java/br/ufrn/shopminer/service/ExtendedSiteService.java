package br.ufrn.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.framework.repository.SiteRepository;
import br.ufrn.shopminer.framework.service.SiteService;
import br.ufrn.shopminer.model.ExtendedSite;
import br.ufrn.shopminer.repository.ExtendedSiteRepository;

@Service
@Transactional(readOnly = true)
public class ExtendedSiteService {
	
	@Autowired
	private ExtendedSiteRepository extendedSiteRepository;	
	
	@Autowired
	private SiteService siteService;
	
	
	
	
	
	public List<ExtendedSite> findAll() {
		return extendedSiteRepository.findAll();
	}
	
	public Optional<ExtendedSite> findOne(Integer id) {
		return extendedSiteRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public ExtendedSite save(ExtendedSite entity) {
		Site site = siteService.findOne(entity.getSite()).get();
		
		entity.setConfig(site.getConfig().getId());
		
		return extendedSiteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(ExtendedSite entity) {
		extendedSiteRepository.delete(entity);
	}

	
}
