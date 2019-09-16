package br.ufrn.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
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
	
	public List<Site> findAll() {
		return siteRepository.findAll();
	}
	
	public Optional<Site> findOne(Integer id) {
		if (siteRepository.findById(id).isPresent())
			return siteRepository.findById(id);
		else
			throw new UnsupportedOperationException("Site não encontrado\n");
	}
	
	@Transactional(readOnly = false)
	public Site save(Site entity) throws Exception {
		/* Try creating a valid URL */
		try {
			new URL(entity.getUrl().replaceAll("[{}]","")).toURI();
		}
		catch (Exception e) {
		    throw new MalformedURLException("URL inválido\n");
		}
		return siteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Site entity) {
		siteRepository.delete(entity);
	}

	
}
