package br.ufrn.shopminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findById(int id);
	
}
