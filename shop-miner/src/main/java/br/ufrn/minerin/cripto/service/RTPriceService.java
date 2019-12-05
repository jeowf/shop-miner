package br.ufrn.minerin.cripto.service;

import br.ufrn.minerin.cripto.model.RTPrice;
import br.ufrn.minerin.cripto.repository.RTPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RTPriceService {
	
	@Autowired
	private RTPriceRepository rtPriceRepository;
	
	public List<RTPrice> findAll() {
		return rtPriceRepository.findAll();
	}


	public Optional<RTPrice> findOne(Integer id) {
		return rtPriceRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public RTPrice save(RTPrice entity) {
		System.out.println("Service is ok. It is the repository");
		return rtPriceRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(RTPrice entity) {
		rtPriceRepository.delete(entity);
	}

}