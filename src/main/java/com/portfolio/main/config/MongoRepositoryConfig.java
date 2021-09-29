package com.portfolio.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.portfolio.main.repository")
public class MongoRepositoryConfig {

}
