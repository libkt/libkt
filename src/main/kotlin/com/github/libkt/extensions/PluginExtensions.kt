package com.github.libkt.extensions

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * Registers all of [EventHandler]s in the given listener for this plugin.
 */
fun Plugin.registerEvents(listener: Listener) {
    server.pluginManager.registerEvents(listener, this)
}

