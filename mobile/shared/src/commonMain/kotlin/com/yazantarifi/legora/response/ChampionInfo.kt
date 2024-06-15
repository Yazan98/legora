package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionInfo(
    @SerialName("name") val name: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("coverImage") val coverImage: String? = "",
    @SerialName("passive") val passive: ChampionPassive? = null,
    @SerialName("allyTips") val allyTips: List<String>? = null,
    @SerialName("enemiesTips") val enemiesTips: List<String>? = null,
    @SerialName("skins") val skins: List<String>? = null,
    @SerialName("spells") val spells: List<ChampionSpell>? = null,
    @SerialName("info") val info: ChampionAbilities? = null,
)

@Serializable
data class ChampionAbilities(
    @SerialName("defense") val defense: Int? = 0,
    @SerialName("attack") val attack: Int? = 0,
    @SerialName("magic") val magic: Int? = 0,
    @SerialName("difficulty") val difficulty: Int? = 0,
)

@Serializable
data class ChampionSpell(
    @SerialName("name") val name: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("image") val image: String? = ""
)

@Serializable
data class ChampionPassive(
    @SerialName("name") val name: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("image") val image: String? = ""
)