package com.rosouza.supplier.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.rosouza.supplier.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
