package com.horobar.campaign.service

import com.horobar.campaign.entities.Campaign
import com.horobar.campaign.repository.CampaignRepository
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class CampaignService(private val campaignRepository: CampaignRepository) {

    fun getCampaignsForUser(userId: UUID): List<Campaign> {
        return campaignRepository.find("gm.id", userId).list()
    }
}