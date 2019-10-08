package br.ufrn.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.shopminer.model.Favorite;
import br.ufrn.shopminer.repository.FavoriteRepository;

@Service
@Transactional(readOnly = true)
public class FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private ScheduleService scheduleService;
	
	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}
	
	public Optional<Favorite> findOne(Integer id) {
		return favoriteRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Favorite save(Favorite entity) {
		scheduleService.addTask(entity);
		return favoriteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Favorite entity) {
		favoriteRepository.delete(entity);
	}

}