package com.github.libkt.bukkit

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import java.util.UUID

/**
 * Finds a player online with the given name.
 *
 * If a player with the exact name is not found, the first player found with a name that starts with the given name will
 * be found instead.
 *
 * It is typically better to find players by using their UUID instead since names can change between login sessions.
 *
 * @return the player with the given name if found online, null otherwise.
 * @since b1
 */
fun findPlayer(name: String): Player? = Bukkit.getPlayer(name)

/**
 * Find a player online with the given UUID.
 *
 * @return a player object if one was found with the given UUID, null otherwise
 * @since b1
 */
fun findPlayer(uuid: UUID): Player? = Bukkit.getPlayer(uuid)

/**
 * Finds an entity on the server with the given UUID.
 *
 * @return the entity with the given UUID, or null if it isn't found
 * @since b1
 */
fun findEntity(uuid: UUID): Entity? = Bukkit.getEntity(uuid)

/**
 * Gets the player with the given name, regardless if they are offline or online.
 *
 * This method may involve a blocking web request to get the UUID for the given name.
 *
 * This will return an object even if the player does not exist. To this method, all players will exist.
 *
 * @since b1
 */
@Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session.",
        ReplaceWith("getOfflinePlayer(UUID)"), DeprecationLevel.WARNING)
fun getOfflinePlayer(name: String): OfflinePlayer = Bukkit.getOfflinePlayer(name)

/**
 * Gets the player by the given UUID, regardless if they are offline or online.
 *
 * This will return an object even if the player does not exist. To this method, all players will exist.
 *
 * @since b1
 */
fun getOfflinePlayer(uuid: UUID): OfflinePlayer = Bukkit.getOfflinePlayer(uuid)