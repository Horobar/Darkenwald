package com.horobar.campaign

import com.horobar.campaign.entities.Campaign
import com.horobar.campaign.repository.CampaignRepository
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class CampaignService(
    private val campaignRepository: CampaignRepository
) {
    fun getAllCampaigns(): List<Campaign> {
        return campaignRepository.listAll()
    }

    fun getCampaign(campaignId: UUID): Campaign {
        return campaignRepository.findById(campaignId)
            ?: throw IllegalArgumentException("Campaign not found")
    }

    fun getCampaignsForUser(userId: UUID): List<Campaign> {
        return campaignRepository.find("gm.id", userId).list()
    }
}
