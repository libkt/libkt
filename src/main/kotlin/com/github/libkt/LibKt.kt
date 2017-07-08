package com.github.libkt

import org.bstats.Metrics
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main plugin class for libkt.
 */
class LibKt : JavaPlugin() {

    /**
     * Returns the build number of the libkt plugin
     *
     * @since b1
     */
    val buildNumber: Int by lazy {
        try {
            description.version.split("-").last().substring(1).toInt()
        } catch(e: Exception) {
            0
        }
    }

    override fun onEnable() {
        setupMetrics()
    }

    private fun setupMetrics() {
        val metrics = Metrics(this)

        metrics.addCustomChart(object : Metrics.SimplePie("kotlin_version") {
            private val kotlinVersion = description.version.split("-")[0]
            override fun getValue() = kotlinVersion
        })
    }
}