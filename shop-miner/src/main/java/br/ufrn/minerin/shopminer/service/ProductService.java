package br.ufrn.minerin.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.minerin.shopminer.model.Product;
import br.ufrn.minerin.shopminer.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findOne(Integer id) {
		return productRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Product save(Product entity) {
		return productRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Product entity) {
		productRepository.delete(entity);
	}
	
	@Transactional(readOnly = false)
	public Product findByName(String name) {
		List<Product> product =  productRepository.findByName(name.replace("+", " "));
		if (product.size() > 0)
			return product.get(0);
		
		return null;
	}
	

}