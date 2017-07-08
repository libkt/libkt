package com.github.libkt.extensions

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID

/**
 * Creates a UUID from this string using standard representation as described in the [UUID.toString] method.
 *
 * @return a UUID from this string representation or null if this string does not conform to the string representation
 * as described in [UUID.toString].
 * @since b1
 */
fun CharSequence.toUUID(): UUID? {
    try {
        return UUID.fromString(this.toString())
    } catch(e: IllegalArgumentException) {
        return null
    }
}

/**
 * Gets the online player who's name is represented by this string.
 *
 * It is typically better to get players by using their UUID instead since names can change between login sessions.
 *
 * @return the player with this name if found online, null otherwise.
 * @since b1
 */
fun CharSequence.getPlayer(): Player? {
    return Bukkit.getPlayer(this.toString())
};

/**
 * Gets the player with this string for a name, regardless if they are offline or online.
 *
 * This method may involve a blocking web request to get the UUID for the this name.
 *
 * This will return an object even if the player does not exist. To this method, all players will exist.
 *
 * @since b1
 */
@Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session.",
        ReplaceWith("Bukkit.getOfflinePlayer(UUID)"), DeprecationLevel.WARNING)
fun CharSequence.getOfflinePlayer(): OfflinePlayer {
    return Bukkit.getOfflinePlayer(this.toString());
}