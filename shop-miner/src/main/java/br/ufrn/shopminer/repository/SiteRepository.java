package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.shopminer.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long>{

	Site findById(long id);
	
}
