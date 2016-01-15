package com.twitter.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.twitter.dao.UserDao;
import com.twitter.dao.UserDaoImpl;
import com.twitter.service.UserService;
import com.twitter.service.UserServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.twitter")
@EnableTransactionManagement
public class PersistanceConfig {
	
	@Bean
	public UserDao userDao(){
		return new UserDaoImpl();
	}
	
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/twitter");
		dataSource.setUsername("postgres");
		dataSource.setPassword("ean38c2tm");
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("com.twitter");
		sessionBuilder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
		sessionBuilder.setProperty("hibernate.hbm2ddl.auto","create");
		sessionBuilder.setProperty("hibernate.show_sql","true");
		SessionFactory sessionFactory = sessionBuilder.buildSessionFactory();
		return sessionFactory;
	}

	@Autowired
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

}
