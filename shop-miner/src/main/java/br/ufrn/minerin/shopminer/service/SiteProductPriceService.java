package br.ufrn.minerin.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.minerin.shopminer.model.SiteProductPrice;
import br.ufrn.minerin.shopminer.repository.SiteProductPriceRepository;

@Service
@Transactional(readOnly = true)
public class SiteProductPriceService {
	
	@Autowired
	private SiteProductPriceRepository siteProductPriceRepository;
	
	public List<SiteProductPrice> findAll() {
		return siteProductPriceRepository.findAll();
	}
	
	public Optional<SiteProductPrice> findOne(Integer id) {
		return siteProductPriceRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public SiteProductPrice save(SiteProductPrice entity) {
		return siteProductPriceRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(SiteProductPrice entity) {
		siteProductPriceRepository.delete(entity);
	}

}