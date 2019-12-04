package br.ufrn.minerin.cripto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.minerin.cripto.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
	Coin findById(int id);
}
