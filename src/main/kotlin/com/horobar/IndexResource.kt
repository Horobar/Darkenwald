package com.horobar

import com.horobar.campaign.CampaignService
import com.horobar.campaign.entities.Campaign
import com.horobar.campaign.character.PlayerChar
import com.horobar.campaign.character.PlayerCharService
import com.horobar.infra.user.UserEntity
import com.horobar.infra.user.UserService
import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import io.quarkus.security.Authenticated
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/")
@Authenticated
class IndexResource(
    private val campaignService: CampaignService,
    private val playerCharService: PlayerCharService,
    private val userService: UserService
) : HxController() {

    @Suppress("FunctionName")
    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(user: UserEntity, characters: List<PlayerChar>, campaigns: List<Campaign>): TemplateInstance

        @JvmStatic
        external fun `index$main`(
            user: UserEntity,
            characters: List<PlayerChar>,
            campaigns: List<Campaign>
        ): TemplateInstance
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("")
    fun index(): TemplateInstance {
        val user = userService.getCurrentUser()
        val characters = playerCharService.getCharactersForUser(user.id)
        val campaigns = campaignService.getCampaignsForUser(user.id)
        if (isHxRequest) {
            return Templates.`index$main`(user, characters, campaigns)
        }
        return Templates.index(user, characters, campaigns)
    }
}