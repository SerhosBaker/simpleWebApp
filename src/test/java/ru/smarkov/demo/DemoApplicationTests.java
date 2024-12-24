package ru.smarkov.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void given_when_then(){
		double givenNum = 1.3;
		double actualNum = 1.4;

		Assertions.assertEquals(givenNum, actualNum);
	}

}
