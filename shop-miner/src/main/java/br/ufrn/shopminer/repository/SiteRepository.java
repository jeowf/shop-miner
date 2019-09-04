package br.ufrn.shopminer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

	Optional<Site> findById(Integer id);

}
