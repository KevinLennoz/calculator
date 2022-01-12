package com.formation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formation.service.Calculator;

@RestController
public class CalculateurRest {
	
	@Autowired
	private Calculator calculator;
	
	@GetMapping("/somme")
	public String somme(@RequestParam("a") int a, @RequestParam("b") int b) {
		return String.valueOf(calculator.somme(a, b));
	}

}
