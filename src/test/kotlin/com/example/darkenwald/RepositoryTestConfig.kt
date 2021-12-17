package com.example.darkenwald

import com.example.darkenwald.repository.RepositoryConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    RepositoryConfig::class
)
@EnableAutoConfiguration()
class RepositoryTestConfig {
}