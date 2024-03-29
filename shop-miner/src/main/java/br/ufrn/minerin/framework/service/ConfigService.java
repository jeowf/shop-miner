package br.ufrn.minerin.framework.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.minerin.framework.model.Config;
import br.ufrn.minerin.framework.repository.ConfigRepository;

@Service
@Transactional(readOnly = false)
public class ConfigService {
	@Autowired
	private ConfigRepository configRepository;

	public List<Config> findAll() {
		return configRepository.findAll();
	}
	
	public Optional<Config> findOne(Integer id) {
		return configRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Config save(Config entity) {
		return configRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Config entity) throws Exception {
		if (entity.getSites() == null)
			configRepository.delete(entity);
		else {
			throw new Exception("Cannot delete. Config is not empty");
		}
	}
}
