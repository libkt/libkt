package com.github.libkt.extensions

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID

/**
 * Gets the online player with this UUID.
 *
 * @return a player object if one was found with this UUID, null otherwise
 * @since b1
 */
fun UUID.getPlayer(): Player? {
    return Bukkit.getPlayer(this)
}

/**
 * Gets the player by this UUID, regardless if they are offline or online.
 *
 * This will return an object even if the player does not exist. To this method, all players will exist.
 *
 * @since b1
 */
fun UUID.getOfflinePlayer(): OfflinePlayer {
    return Bukkit.getOfflinePlayer(this);
}