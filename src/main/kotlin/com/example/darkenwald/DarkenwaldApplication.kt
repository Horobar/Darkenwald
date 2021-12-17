package com.example.darkenwald

import com.example.darkenwald.repository.RepositoryConfig
import org.apache.catalina.security.SecurityConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.web.context.WebApplicationContext

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCaching
@Import(
    RepositoryConfig::class,
    SecurityConfig::class,
)
@EntityScan(basePackages = ["com.example.darkenwald.*"])
@ComponentScan(basePackages = ["com.example.darkenwald.*"])
class DarkenwaldApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<DarkenwaldApplication>(*args)
}
