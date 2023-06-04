package tobyspring.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
class HelloRepositoryJdbcTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HelloRepository helloRepository;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	@Test
	void findHelloFailed() {
		// given
		Hello hello = helloRepository.findHello("Toby");
		assertThat(hello).isNull();
		// when

		// then
	}

	@Test
	void increaseCount() {
		// given
		assertThat(helloRepository.countOf("Toby")).isEqualTo(0);
		helloRepository.increaseCount("Toby");
		assertThat(helloRepository.countOf("Toby")).isEqualTo(1);
		helloRepository.increaseCount("Toby");
		assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
		// when

		// then
	}
}
