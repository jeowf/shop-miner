package br.ufrn.minerin.ttminer.controller;

import br.ufrn.minerin.ttminer.model.Topic;
import br.ufrn.minerin.ttminer.model.TrendingTopics;
import br.ufrn.minerin.ttminer.service.TrendingTopicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import br.ufrn.shopminer.model.Site;
//import br.ufrn.shopminer.service.SiteService;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Trending Topics")
@CrossOrigin(origins="*")
public class TTController {
	
	@Autowired
	private TrendingTopicsService trendingTopicsService;

	@GetMapping("/trendingTopics")
	@ApiOperation(value = "Returns a list of All Topics")
	public ResponseEntity<List<TrendingTopics>> getTopics() {
		List<TrendingTopics> trendingTopics;
		ResponseEntity<List<TrendingTopics>> re;

		try {
			trendingTopics = trendingTopicsService.findAll();
			re = new ResponseEntity<List<TrendingTopics>> (trendingTopics, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/trendingTopics/{location}")
	@ApiOperation(value = "Returns a list of TrendingTopics by location")
	public ResponseEntity<List<TrendingTopics>> getTopicsBylocation(@PathVariable("location") String location) {
		List<TrendingTopics> trendingTopics;
		ResponseEntity<List<TrendingTopics>> re;

		try {
			trendingTopics = trendingTopicsService.findAllByLocation(location);
			re = new ResponseEntity<List<TrendingTopics>> (trendingTopics, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/subject/{word}")
	@ApiOperation(value = "Returns a list of Topics")
	public ResponseEntity<List<TrendingTopics>> getTopicsBySubject(@PathVariable("word") String subject) {
		List<TrendingTopics> trendingTopics;
		List<TrendingTopics> trendingTopicsAnswer = new ArrayList<>();
		ResponseEntity<List<TrendingTopics>> re;

		try {
			trendingTopics = trendingTopicsService.findAll();
			for ( TrendingTopics tts : trendingTopics ) {
				for ( Topic topic : tts.getTopics() ){
					if ( topic.getSubject().toLowerCase().equals(subject.toLowerCase()) ) {
					    trendingTopicsAnswer.add( tts );
					    break;
					}
				}
			}
			re = new ResponseEntity<List<TrendingTopics>> (trendingTopicsAnswer, HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}

		return re;
	}

	@GetMapping("/trendingTopics/id/{id}")
	@ApiOperation(value = "Returns a Topic by id")
	public ResponseEntity<List<Topic>> getTopic(@PathVariable("id") Integer id) {
		TrendingTopics trendingTopics;
		ResponseEntity<List<Topic>> re;
		
		try {
			trendingTopics = trendingTopicsService.findOne(id).get();
			re = new ResponseEntity<List<Topic>> (trendingTopics.getTopics(), HttpStatus.FOUND);
		} catch (Exception e) {
			re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	
		return re;
	}
	
	//@DeleteMapping("/tts")
	//@ApiOperation(value = "Deletes a Topic")
	//public ResponseEntity<Topic> deleteTopic(@RequestBody Topic price){
	//	ResponseEntity<Topic> re;

	//	try {
	//		trendingTopicsService.delete(price);
	//		re = new ResponseEntity<> (price, HttpStatus.OK);
	//	} catch (Exception e) {
	//		re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
	//	}

	//	return re;

	//}
	//@PutMapping("/tts")
	//@ApiOperation(value = "Updates a Topic")
	//public ResponseEntity<Topic> putTopic(@RequestBody Topic price){
	//	ResponseEntity<Topic> re;

	//	try {
	//		trendingTopicsService.save(price);
	//		re = new ResponseEntity<> (price, HttpStatus.OK);
	//	} catch (Exception e) {
	//		re = new ResponseEntity<> (null, HttpStatus.NOT_ACCEPTABLE);
	//	}

	//	return re;
	//}
}
