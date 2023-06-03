package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@HellobootTest
class DataSourceConfigTest {
	@Autowired
	DataSource dataSource;

	@Test
	void connect() throws SQLException {
		// given
		Connection connection = dataSource.getConnection();
		connection.close();
		// when

		// then
	}
}
