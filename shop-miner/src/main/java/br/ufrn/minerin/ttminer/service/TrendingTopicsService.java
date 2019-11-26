package br.ufrn.minerin.ttminer.service;

import br.ufrn.minerin.ttminer.model.TrendingTopics;
import br.ufrn.minerin.ttminer.repository.TrendingTopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrendingTopicsService {
	
	@Autowired
	private TrendingTopicsRepository trendingTopicsRepository;
	
	public List<TrendingTopics> findAll() {
		return trendingTopicsRepository.findAll();
	}
	
	public Optional<TrendingTopics> findOne(Integer id) {
		return trendingTopicsRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public TrendingTopics save(TrendingTopics entity) {
		System.out.println("Service is ok. It is the repository");
		return trendingTopicsRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(TrendingTopics entity) {
		trendingTopicsRepository.delete(entity);
	}

}