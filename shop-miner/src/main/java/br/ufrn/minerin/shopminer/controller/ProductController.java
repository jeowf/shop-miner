package br.ufrn.minerin.shopminer.controller;

import br.ufrn.minerin.shopminer.model.Product;
//import br.ufrn.shopminer.model.Site;
import br.ufrn.minerin.shopminer.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import br.ufrn.shopminer.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	@ApiOperation(value = "Returns a list of Product")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> configs;
		ResponseEntity<List<Product>> re;

		try {
			configs = productService.findAll();
			re = new ResponseEntity<> (configs, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/product/{id}")
	@ApiOperation(value = "Returns a Product by id")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
		Product product;
		ResponseEntity<Product> re;
		
		try {
			product = productService.findOne(id).get();
			re = new ResponseEntity<> (product, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@GetMapping("/product/name/{name}")
	@ApiOperation(value = "Returns a Product by id")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
		Product product;
		ResponseEntity<Product> re;
		
		try {
			product = productService.findByName(name);
			re = new ResponseEntity<> (product, HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/product")
	@ApiOperation(value = "Saves a new Product")
	public ResponseEntity<Product> postProduct(@RequestBody Product product){
		ResponseEntity<Product> re;
		
		try {
			productService.save(product);
			re = new ResponseEntity<> (product, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

	@DeleteMapping("/product")
	@ApiOperation(value = "Deletes a Product")
	public ResponseEntity<Product> deleteProduct(@RequestBody Product product){
		ResponseEntity<Product> re;

		try {
			productService.delete(product);
			re = new ResponseEntity<> (product, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;

	}
	@PutMapping("/product")
	@ApiOperation(value = "Updates a Product")
	public ResponseEntity<Product> putProduct(@RequestBody Product product){
		ResponseEntity<Product> re;

		try {
			productService.save(product);
			re = new ResponseEntity<> (product, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}

		return re;
	}
}
