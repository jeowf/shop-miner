package br.ufrn.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.repository.PriceRepository;

@Service
@Transactional(readOnly = true)
public class PriceService {
	
	@Autowired
	private PriceRepository priceRepository;
	
	public List<Price> findAll() {
		return priceRepository.findAll();
	}
	
	public Optional<Price> findOne(Integer id) {
		return priceRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Price save(Price entity) {
		return priceRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Price entity) {
		priceRepository.delete(entity);
	}

}