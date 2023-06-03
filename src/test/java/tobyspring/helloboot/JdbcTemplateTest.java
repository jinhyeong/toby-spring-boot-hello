package tobyspring.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
@Rollback(value = false)
class JdbcTemplateTest {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	@Test
	void insertAndQuery() {
		jdbcTemplate.update("insert into hello values (?,?)", "Toby", 3);
		jdbcTemplate.update("insert into hello values (?,?)", "Spring", 1);

		Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);

		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from hello");
		System.out.println("maps = " + maps);

		assertThat(count).isEqualTo(2);
	}

	@Test
	void insertAndQuery2() {
		jdbcTemplate.update("insert into hello values (?,?)", "Toby", 3);
		jdbcTemplate.update("insert into hello values (?,?)", "Spring", 1);

		Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from hello");
		System.out.println("maps = " + maps);
		assertThat(count).isEqualTo(2);
	}
}
