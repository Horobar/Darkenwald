package com.horobar.campaign.character

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PlayerCharRepository: PanacheRepositoryBase<PlayerChar, UUID> {
    fun findByName(name: String): PlayerChar? {
        return find("name", name).firstResult()
    }
}