package br.ufrn.minerin.shopminer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.minerin.shopminer.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findById(int id);
	
	List<Product> findByName(String name);
	
}
