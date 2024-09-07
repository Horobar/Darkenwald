package com.horobar.campaign.repository

import com.horobar.campaign.entities.Scene
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class SceneRepository: PanacheRepositoryBase<Scene, UUID> {
    fun findByName(name: String): Scene? {
        return find("name", name).firstResult()
    }
}