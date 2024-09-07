package com.horobar.campaign.entities

import jakarta.persistence.*
import java.util.*

@Entity
class Scene {
    @Id
    @GeneratedValue
    lateinit var id: UUID

    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    lateinit var campaign: Campaign
}