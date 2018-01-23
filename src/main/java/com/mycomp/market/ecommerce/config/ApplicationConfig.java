package com.mycomp.market.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.mycomp.market.ecommerce.repository")
public class ApplicationConfig {

}
