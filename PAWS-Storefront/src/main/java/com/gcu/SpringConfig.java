package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

import com.gcu.business.AuthService;
import com.gcu.business.ProductService;
import com.gcu.business.RegistrationService;
import com.gcu.repository.ProductDao;

/**
 * Spring configuration class for the application.
 */
@Configuration
public class SpringConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * Bean for JdbcTemplate.
     *
     * @return a JdbcTemplate instance
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Bean for AuthService.
     *
     * @return an AuthService instance
     */
    @Bean
    public AuthService authService() {
        return new AuthService();
    }

    /**
     * Bean for RegistrationService.
     *
     * @return a RegistrationService instance
     */
    @Bean
    public RegistrationService registrationService() {
        return new RegistrationService();
    }

    /**
     * Bean for ProductService.
     *
     * @param productDao the ProductDao object
     * @return a ProductService instance
     */
    @Bean
    public ProductService productService(ProductDao productDao) {
        return new ProductService(productDao);
    }

    /**
     * Bean for ProductDao.
     *
     * @param jdbcTemplate the JdbcTemplate object
     * @return a ProductDao instance
     */
    @Bean
    public ProductDao productDao(JdbcTemplate jdbcTemplate) {
        return new ProductDao(jdbcTemplate);
    }
}