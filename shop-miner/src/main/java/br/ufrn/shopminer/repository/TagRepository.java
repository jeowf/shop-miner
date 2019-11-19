package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	Tag findById(int id);
}
