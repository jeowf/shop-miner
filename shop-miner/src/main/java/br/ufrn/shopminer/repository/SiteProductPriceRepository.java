package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.SiteProductPrice;

@Repository
public interface SiteProductPriceRepository  extends JpaRepository<SiteProductPrice, Integer> {

	SiteProductPrice findById(int id);
	
}
