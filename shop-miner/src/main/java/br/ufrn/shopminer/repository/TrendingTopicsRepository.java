package br.ufrn.shopminer.repository;

import br.ufrn.shopminer.model.SiteProductPrice;
import br.ufrn.shopminer.model.TrendingTopics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendingTopicsRepository extends JpaRepository<TrendingTopics, Integer> {

	SiteProductPrice findById(int id);
	
}
