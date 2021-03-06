package com.example.darkenwald.repository


import com.example.darkenwald.domains.PlayerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<PlayerEntity, Long> {

    fun findByNameAndPassword(name: String, password: String): PlayerEntity?

    fun findByName(name: String): PlayerEntity?
}