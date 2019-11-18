package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.ExtendedSite;

@Repository
public interface ExtendedSiteRepository extends JpaRepository<ExtendedSite, Integer>{

	ExtendedSite findById(int id);
	
}
