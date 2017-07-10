package com.github.libkt.extensions

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * Extension functions and properties that pertain to [Plugin].
 */
object Plugin {

    /**
     * Registers all of [EventHandler]s in the given listener class for this plugin.
     *
     * @since b1
     */
    fun Plugin.registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    /**
     * Attempts to set whether this plugin is enabled or disabled.
     *
     * Any attempt to enable an already enabled plugin or disable an already disabled plugin will do nothing.
     *
     * @since b1
     */
    fun Plugin.setEnabled(enabled: Boolean) {
        val pluginManager = server.pluginManager
        if (enabled) pluginManager.enablePlugin(this) else pluginManager.disablePlugin(this)
    }
}