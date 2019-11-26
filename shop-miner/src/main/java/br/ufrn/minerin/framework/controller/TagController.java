package br.ufrn.minerin.framework.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.minerin.framework.model.Tag;
import br.ufrn.minerin.framework.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Tag")
@CrossOrigin(origins="*")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	
	@GetMapping("/tag")
	@ApiOperation(value = "Returns a list of Tag")
	public ResponseEntity<List<Tag>> getTags() {
		List<Tag> tags;
		ResponseEntity<List<Tag>> re;
		
		try {
			tags = tagService.findAll();
			re = new ResponseEntity<> (tags, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@GetMapping("/tag/{id}")
	@ApiOperation(value = "Returns a Tag by id")
	public ResponseEntity<Tag> getTag(@PathVariable("id") Integer id) {
		Tag tag;
		ResponseEntity<Tag> re;
		
		try {
			tag = tagService.findOne(id).get();
			re = new ResponseEntity<> (tag, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	@PostMapping("/tag")
	@ApiOperation(value = "Saves a new Tag")
	public ResponseEntity<Tag> postTag(@RequestBody Tag tag){
		ResponseEntity<Tag> re;
		
		//re = new ResponseEntity<>(null, HttpStatus.OK);
		
		try {
			tagService.save(tag);
			re = new ResponseEntity<> (tag, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}
	
	//@PutMapping("/tag")
	@DeleteMapping("/tag")
	@ApiOperation(value = "Deletes a Tag")
	public ResponseEntity<Tag> deleteTag(@RequestBody Tag tag){
		ResponseEntity<Tag> re;
		
		try {
			tagService.delete(tag);
			re = new ResponseEntity<> (tag, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;	
		
	}
	
	@PutMapping("/tag")
	@ApiOperation(value = "Updates a Tag")
	public ResponseEntity<Tag> putTag(@RequestBody Tag tag){
		ResponseEntity<Tag> re;

		try {
			tagService.save(tag);
			re = new ResponseEntity<> (tag, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return re;
	}

}
