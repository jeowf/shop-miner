package br.ufrn.shopminer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer> {

	Config findById(int id);

}
