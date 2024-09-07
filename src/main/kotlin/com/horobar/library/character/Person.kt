package com.horobar.library.character

import com.horobar.library.article.Article
import jakarta.persistence.*
import java.util.UUID

@Entity
class Person {

    @Id
    @GeneratedValue
    var id: UUID? = null

    lateinit var name: String

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "article_id")
    var article: Article? = null


    companion object {
        fun examples() = listOf(
            Person().apply {
                name = "Lavinia"
                article = Article().apply {
                    title = "Lavinia"
                    content ="""
                    ## Lavinia, Azorius Senate
                        

                    ![Lavinia](https://api.placeholder.com/300x200)

                    Lavinia, the current acting guildmaster of the Azorius Senate, stands as a paragon of law and order in Ravnica.

                    ### Key Traits
                    - **Alignment**: Lawful Neutral
                    - **Class**: Fighter/Wizard
                    - **Signature Spell**: Hieromancer's Judgment

                    Lavinia is known for her unwavering commitment to justice and her exceptional skill in both martial and magical combat. As the leader of the Azorius, she strives to maintain peace and order throughout Ravnica, often finding herself at odds with the more chaotic elements of the plane.

                    > "The law is not just a set of rules, but a living, breathing entity that protects us all."
                    """.trimIndent()
                }
            },
            Person().apply {
                name = "Aurelia"
                article = Article().apply {
                    title = "The Warleader"
                    content =
                        "Aurelia is the parun of the Boros Legion, a powerful angel warrior who leads her troops into battle."
                }
            },
            Person().apply {
                name = "Lazav"
                article = Article().apply {
                    title = "The Multifarious"
                    content =
                        "Lazav is the guildmaster of House Dimir, a shapeshifting spy who manipulates events from the shadows."
                }
            }
        )
    }
}

