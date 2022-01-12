package com.formation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.formation.service.Calculator;

@SpringBootTest
class DemoApplicationTests {
	
	private Calculator calcul = new Calculator();

	@Test
	void testSum() {
		assertEquals(5, calcul.somme(2, 3));
	}

}
