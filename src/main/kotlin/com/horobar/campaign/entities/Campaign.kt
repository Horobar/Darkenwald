package com.horobar.campaign.entities


import com.horobar.campaign.character.PlayerChar
import com.horobar.infra.user.UserEntity
import jakarta.persistence.*
import java.util.*

/**
 * A campaign is the root container to organize an adventure.
 */
@Entity
class Campaign {
    @Id
    @GeneratedValue
    lateinit var id: UUID

    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "gm_id")
    lateinit var gm: UserEntity

    @OneToMany(
        targetEntity = PlayerChar::class,
        fetch = FetchType.LAZY,
        mappedBy = "campaign")
    var players: Set<PlayerChar> = emptySet()


    @OneToMany(
        targetEntity = Setting::class,
        fetch = FetchType.LAZY,
        mappedBy = "campaign"
    )
    var settings: Set<Setting> = emptySet()
}