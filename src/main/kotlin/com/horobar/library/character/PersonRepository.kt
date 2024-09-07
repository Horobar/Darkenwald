package com.horobar.library.character

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PersonRepository: PanacheRepository<Person> {
    fun findById(id: UUID): Person? {
        return find("id", id).firstResult()
            ?: findAll().firstResult()

    }
}