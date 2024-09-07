package com.horobar.campaign.repository

import com.horobar.campaign.entities.Campaign
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class CampaignRepository: PanacheRepositoryBase<Campaign, UUID> {
    fun findByName(name: String): Campaign? {
        return find("name", name).firstResult()
    }
}