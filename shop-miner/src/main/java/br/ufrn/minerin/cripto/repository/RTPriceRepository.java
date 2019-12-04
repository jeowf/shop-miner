package br.ufrn.minerin.cripto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.minerin.cripto.model.RTPrice;

@Repository
public interface RTPriceRepository extends JpaRepository<RTPrice, Integer>{

	RTPrice findById(int id);
	
}
