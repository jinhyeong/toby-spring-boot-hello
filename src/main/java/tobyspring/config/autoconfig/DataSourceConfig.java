package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
public class DataSourceConfig {
	@Bean
	@ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
	DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		return dataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	DataSource hikariDataSource(MyDataSourceProperties properties){
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName(properties.getDriverClassName());
		hikariDataSource.setJdbcUrl(properties.getUrl());
		hikariDataSource.setUsername(properties.getUsername());
		hikariDataSource.setPassword(properties.getPassword());
		return hikariDataSource;
	}

}