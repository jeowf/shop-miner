package br.ufrn.minerin.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.minerin.framework.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	Tag findById(int id);
}
