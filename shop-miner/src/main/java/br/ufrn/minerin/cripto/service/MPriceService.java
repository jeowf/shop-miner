package br.ufrn.minerin.cripto.service;

import br.ufrn.minerin.cripto.model.MPrice;
import br.ufrn.minerin.cripto.repository.MPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MPriceService {
	
	@Autowired
	private MPriceRepository mPriceRepository;
	
	public List<MPrice> findAll() {
		return mPriceRepository.findAll();
	}


	public Optional<MPrice> findOne(Integer id) {
		return mPriceRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public MPrice save(MPrice entity) {
		System.out.println("Service is ok. It is the repository");
		return mPriceRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(MPrice entity) {
		mPriceRepository.delete(entity);
	}

}