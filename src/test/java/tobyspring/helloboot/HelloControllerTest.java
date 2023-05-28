package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {
	@Test
	void helloController() {
		// given
		HelloController helloController = new HelloController(name -> name);

		// when
		String result = helloController.hello("Test");

		// then
		assertThat(result).isEqualTo("Test");
	}

	@Test
	void failsHelloController() {
		// given
		HelloController helloController = new HelloController(name -> name);

		Assertions.assertThatThrownBy(() -> {
			// when
			String result = helloController.hello(null);
		}).isInstanceOf(IllegalArgumentException.class);

		Assertions.assertThatThrownBy(() -> {
			// when
			String result = helloController.hello("");
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
