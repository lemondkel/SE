package com.l2jong.se.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.l2jong.se.dao")
public class H2ServerConfiguration {
	@Bean
	public DataSource dataSource() throws SQLException {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName("org.h2.Driver");
		hikariDataSource.setJdbcUrl("jdbc:h2:tcp://localhost:9092/~/sedb");
		hikariDataSource.setUsername("se");
		hikariDataSource.setPassword("1234");
		Server.createTcpServer("-tcp",
				"-tcpPort",
				"9092",
				"-tcpAllowOthers",
				"-ifNotExists"
		).start();
		// schema init
		Resource initSchema = new ClassPathResource("init-sql/schema-h2.sql");
		Resource initData = new ClassPathResource("init-sql/data-h2.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
		DatabasePopulatorUtils.execute(databasePopulator, hikariDataSource);

		return hikariDataSource;
	}
}
