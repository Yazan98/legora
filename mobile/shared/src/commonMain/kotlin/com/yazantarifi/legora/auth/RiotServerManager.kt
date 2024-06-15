package com.yazantarifi.legora.auth

import com.yazantarifi.legora.models.RiotRegion
import com.yazantarifi.legora.models.RiotServer

object RiotServerManager {

    fun getServersList(): List<RiotServer> {
        return arrayListOf(
            RiotServer("North America", "na1"),
            RiotServer("Europe West", "euw1"),
            RiotServer("Europe Nordic & East", "eun1"),
            RiotServer("Brazil", "br1"),
            RiotServer("Korea", "kr"),
            RiotServer("Latin America North", "la1"),
            RiotServer("Latin America South", "la2"),
            RiotServer("Oceania", "oc1"),
            RiotServer("Russia", "ru"),
            RiotServer("Turkey", "tr1"),
            RiotServer("Japan", "jp1"),
        )
    }

    fun getServerRegions(): List<RiotRegion> {
        return arrayListOf(
            RiotRegion("Americas", "americas"),
            RiotRegion("Asia", "asia"),
            RiotRegion("Europe", "europe"),
            RiotRegion("SEA", "sea"),
        )
    }

}
