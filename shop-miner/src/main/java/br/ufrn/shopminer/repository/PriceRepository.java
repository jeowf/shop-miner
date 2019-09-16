package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Price;

@Repository
public interface PriceRepository  extends JpaRepository<Price, Integer> {
	
	Price findById(int id);
	
}
