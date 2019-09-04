package br.ufrn.shopminer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api")
@Api(value = "API REST Site")
@CrossOrigin(origins="*")
public class HomeController {
	
	@RequestMapping("/")
	@ApiOperation(value = "Retorna os dados do Site")
	public String home() {
		return "home";
	}
}
