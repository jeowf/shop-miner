package br.ufrn.minerin.cripto.service;

import br.ufrn.minerin.cripto.model.Coin;
import br.ufrn.minerin.cripto.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {
	
	@Autowired
	private CoinRepository coinRepository;
	
	public List<Coin> findAll() {
		return coinRepository.findAll();
	}


	public Optional<Coin> findOne(Integer id) {
		return coinRepository.findById(id);
	}
	
	public Coin findByCode(String cod) {
		return coinRepository.findByCod(cod);
	}
	
	@Transactional(readOnly = false)
	public Coin save(Coin entity) {
		System.out.println("Service is ok. It is the repository");
		return coinRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Coin entity) {
		coinRepository.delete(entity);
	}

}