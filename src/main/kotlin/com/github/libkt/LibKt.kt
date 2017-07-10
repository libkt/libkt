package com.github.libkt

import com.github.libkt.extensions.plugin.registerEvents
import com.github.libkt.extensions.plugin.setEnabled
import org.bstats.Metrics
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginEnableEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main plugin class for libkt.
 */
class LibKt : JavaPlugin(), Listener {

    /**
     * Returns the build number of the libkt plugin
     *
     * @since b1
     */
    val buildNumber: Int by lazy {
        try {
            description.version.split("-").last().substring(1).toInt()
        } catch(e: Exception) {
            logger.warning("libkt build number could not be detected - dependent plugins may not function correctly!")
            0
        }
    }

    override fun onEnable() {
        if (buildNumber > 0) {
            registerEvents(this)
        }
        setupMetrics()
    }

    private fun setupMetrics() {
        val metrics = Metrics(this)

        metrics.addCustomChart(object : Metrics.SimplePie("kotlin_version") {
            private val kotlinVersion = description.version.split("-")[0]
            override fun getValue() = kotlinVersion
        })
    }

    @EventHandler
    fun pluginEnable(event: PluginEnableEvent) {
        val plugin = event.plugin
        if (plugin is KPlugin) {
            if (buildNumber < plugin.requiredLibktBuild) {
                logger.severe("$plugin is being disabled as it requires b${plugin.requiredLibktBuild} or greater of" +
                        " libkt (found b$buildNumber)")
                plugin.setEnabled(false)
            }
        }
    }
}