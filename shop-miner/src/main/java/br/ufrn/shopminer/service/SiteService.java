package br.ufrn.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.repository.SiteRepository;

@Service
@Transactional(readOnly = true)
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Transactional(readOnly = false)
	public List<Site> findAll() {
		return siteRepository.findAll();
	}
	
	public Optional<Site> findOne(Integer id) {
		return siteRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Site save(Site entity) {
		return siteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Site entity) {
		siteRepository.delete(entity);
	}

	public Site findById(Integer id) {
		return siteRepository.findById(id.intValue());
		//return null;
	}
	
}
