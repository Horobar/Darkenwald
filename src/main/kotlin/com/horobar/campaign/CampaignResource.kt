package com.horobar.campaign

import com.horobar.campaign.entities.Campaign
import com.horobar.campaign.character.PlayerChar
import com.horobar.campaign.entities.Setting
import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import java.util.*

@Path("/dm")
class CampaignResource(
    private val campaignService: CampaignService
) : HxController() {


    @Suppress("FunctionName", "unused")
    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun dmView(campaigns: List<Campaign>): TemplateInstance
        @JvmStatic
        external fun `dmView$main`(campaigns: List<Campaign>): TemplateInstance
        @JvmStatic
        external fun `fragments$setting_select`(settings: Set<Setting>): TemplateInstance
        @JvmStatic
        external fun `fragments$player_show`(players: Set<PlayerChar>): TemplateInstance
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("")
    fun dmView(): TemplateInstance {
        val campaigns = campaignService.getAllCampaigns()
        if (isHxRequest) {
            return Templates.`dmView$main`(campaigns)
        }
        return Templates.dmView(campaigns)
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/campaign")
    fun selectCampaign(
        @QueryParam("campaign")
        id: String
    ): TemplateInstance {
        onlyHxRequest()
        val campaign = campaignService.getCampaign(UUID.fromString(id))
        return concatTemplates(
            Templates.`fragments$setting_select`(campaign.settings),
            Templates.`fragments$player_show`(campaign.players)
        )
    }

}