package com.horobar.library

import com.horobar.library.character.PersonDomain
import com.horobar.library.model.Category
import com.horobar.library.model.Link
import com.horobar.library.character.PersonRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class LibraryService(
    private val personRepository: PersonRepository
) {

    fun allCategories(): List<Category> {
        val persons = personRepository.listAll()
        val personCategory = Category(
            "Persons",
            persons.map {
                Link(
                    it.id!!,
                    it.name,
                    PersonDomain.BASE_PATH
                )
            })
        return listOf(personCategory)
    }

    fun category(name: String): List<Category> {
        val persons = personRepository.listAll()
        val personCategory = Category(
            "Persons",
            persons.map {
                Link(
                    it.id!!,
                    it.name,
                    PersonDomain.BASE_PATH
                )
            })
        return listOf(personCategory)
    }
}