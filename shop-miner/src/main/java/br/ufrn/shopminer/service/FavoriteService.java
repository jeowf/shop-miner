package br.ufrn.shopminer.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.shopminer.model.Favorite;
import br.ufrn.shopminer.repository.FavoriteRepository;

@Service
@Transactional(readOnly = false)
public class FavoriteService {
	@Autowired
	private FavoriteRepository favoriteRepository;

	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}
	
	public Optional<Favorite> findOne(Integer id) {
		return favoriteRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Favorite save(Favorite entity) {
		return favoriteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Favorite entity) throws Exception {
		/*
		if (entity.getConfigs() != null)
			favoriteRepository.delete(entity);
		else {
			throw new Exception("Cannot delete. Config is not empty");
		}
		*/
	}
}
