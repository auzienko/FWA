package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.*;
import edu.school21.cinema.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "edu.school21.cinema")
@PropertySource("classpath:../application.properties")
public class AppConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driverClassName;
    @Value("${storage.path}")
    private String storagePath;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public UsersRepository usersRepository(JdbcTemplate jdbcTemplate) {
        return new UsersRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public UserAuthHistoryRepository userAuthHistoryRepository(JdbcTemplate jdbcTemplate) {
        return new UserAuthHistoryRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public UsersAvatarRepository usersAvatarRepository(JdbcTemplate jdbcTemplate) {
        return new UsersAvatarRepositoryImpl(jdbcTemplate);
    }
    @Bean
    public UsersService usersService(UsersRepository usersRepository, PasswordEncoder bCryptEncoder) {
        return new UserServiceImpl(usersRepository, bCryptEncoder);
    }

    @Bean
    public UserAuthHistoryService userAuthHistoryService(UserAuthHistoryRepository userAuthHistoryRepository) {
        return new UserAuthHistoryServiceImpl(userAuthHistoryRepository);
    }

    @Bean
    public UserAvatarService userAvatarService(UsersAvatarRepository usersAvatarRepository) {
        return new UserAvatarServiceImpl(usersAvatarRepository, storagePath);
    }
}
