package br.ufrn.minerin.cripto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.minerin.cripto.model.MPrice;

public interface MPriceRepository extends JpaRepository<MPrice, Integer> {
	MPrice findById(int id);
}
