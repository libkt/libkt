package com.github.libkt.extensions

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * Extension functions and properties that pertain to [Plugin].
 */
object Plugin {

    /**
     * Registers all of [EventHandler]s in the given listener for this plugin.
     *
     * @since b1
     */
    fun Plugin.registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }
}