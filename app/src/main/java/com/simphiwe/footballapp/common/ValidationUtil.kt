package com.simphiwe.footballapp.common

import com.simphiwe.footballapp.data.model.player.Player

object ValidationUtil {

    fun validatePlayer(player: Player) : Boolean {
        if (player.name.isNotEmpty() && player.nationality.isNotEmpty()) {
            return true
        }
        return false
    }
}
