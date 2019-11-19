package br.ufrn.shopminer.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.framework.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer> {

	Config findById(int id);

}
