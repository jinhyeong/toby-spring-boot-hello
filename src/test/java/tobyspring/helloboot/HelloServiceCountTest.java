package tobyspring.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
public class HelloServiceCountTest {
	@Autowired
	private HelloService helloService;
	@Autowired
	private HelloRepository helloRepository;


	@BeforeEach
	void setUp() {
	}

	@Test
	void sayHelloIncreaseCount() {
		// given
		helloService.sayHello("Toby");
		assertThat(helloRepository.countOf("Toby")).isEqualTo(1);

		helloService.sayHello("Toby");
		assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
		// when


		// then
	}
}
