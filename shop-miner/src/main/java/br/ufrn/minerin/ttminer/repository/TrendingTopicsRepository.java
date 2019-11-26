package br.ufrn.minerin.ttminer.repository;

import br.ufrn.minerin.shopminer.model.SiteProductPrice;
import br.ufrn.minerin.ttminer.model.TrendingTopics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendingTopicsRepository extends JpaRepository<TrendingTopics, Integer> {

	SiteProductPrice findById(int id);
	
}
