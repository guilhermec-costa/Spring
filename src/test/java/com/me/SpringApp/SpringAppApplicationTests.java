package com.me.SpringApp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SpringAppApplication.class)
class SpringAppApplicationTests {

	@Test
	@DisplayName("Should add numbers")
	void shouldAddNumbers() {
		// given - when - then
		int result = Calculator.add(5, 5);
		assertThat(result).isEqualTo(10);
	}

	private class Calculator {
		static int add(int a, int b) {
			return a + b;
		}
	}

}
