package com.linkedin.user_service.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import org.neo4j.driver.Driver;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    // PostgreSQL Transaction Manager
    @Bean(name = {"transactionManager", "postgresTransactionManager"})
    @Primary
    public PlatformTransactionManager postgresTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    // Neo4j Transaction Manager
    @Bean(name = "neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager(Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}


