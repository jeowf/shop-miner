package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	Favorite findById(int id);

}
