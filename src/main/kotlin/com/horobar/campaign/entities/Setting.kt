package com.horobar.campaign.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

/**
 * A setting is a specific version of world or universe associated to one or more campaings.
 * For example, if more than one campaign is set in the Forgotten Realms, then there could be changes in on version
 * that don't affect the others.
 */
@Entity
data class Setting(
    @Id
    @GeneratedValue
    val id: UUID? = null,
    val name: String = "no name given",

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    val campaign: Campaign? = null
)
