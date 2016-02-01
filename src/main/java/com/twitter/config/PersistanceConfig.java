package com.twitter.config;

import com.twitter.dao.TweetDao;
import com.twitter.dao.TweetDaoImpl;
import com.twitter.dao.UserDao;
import com.twitter.dao.UserDaoImpl;
import com.twitter.service.TweetService;
import com.twitter.service.TweetServiceImpl;
import com.twitter.service.UserService;
import com.twitter.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.twitter")
@EnableTransactionManagement
@PropertySource("classpath:/hibernate.properties")
public class PersistanceConfig {

    @Autowired
    public Environment env;

    @Bean
    public UserDao userDao(SessionFactory sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    @Bean
    public TweetDao tweetDao(SessionFactory sessionFactory) {
        return new TweetDaoImpl(sessionFactory);
    }

    @Bean
    public TweetService tweetService(UserDao userDao, TweetDao tweetDao) {
        return new TweetServiceImpl(userDao, tweetDao);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("com.twitter");
        sessionBuilder.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        sessionBuilder.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        sessionBuilder.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
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
