package com.example.darkenwald.repository

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = [MessageRepository::class, PlayerRepository::class])
@EnableTransactionManagement
class RepositoryConfig {
}