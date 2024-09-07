package com.horobar.campaign.character

import com.horobar.campaign.entities.Campaign
import com.horobar.infra.user.UserEntity
import jakarta.persistence.*
import java.util.*


@Entity
class PlayerChar {
    @Id
    @GeneratedValue
    lateinit var id: UUID

    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "user_id")
    lateinit var user: UserEntity

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    lateinit var campaign: Campaign

}