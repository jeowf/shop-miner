package br.ufrn.shopminer.framework.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.shopminer.framework.model.Tag;
import br.ufrn.shopminer.framework.repository.TagRepository;

@Service
@Transactional(readOnly = true)
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}
	
	public Optional<Tag> findOne(Integer id) {
		return tagRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Tag save(Tag entity) {
		return tagRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Tag entity) {
		tagRepository.delete(entity);
	}

	
}
