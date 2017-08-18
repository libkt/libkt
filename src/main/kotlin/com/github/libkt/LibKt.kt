/*
 * This file is part of libkt.
 *
 * Copyright (c) 2017 libkt team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.libkt

import org.bstats.Metrics
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginEnableEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.InputStreamReader

/**
 * The main plugin class for libkt.
 */
class LibKt : JavaPlugin(), Listener {

    /**
     * The build number of the libkt plugin which can be used to gauge which features are available in this plugin.
     *
     * @since b1
     */
    val buildNumber: Int by lazy {
        try {
            description.version.toInt()
        } catch(e: Exception) {
            logger.warning("libkt build number is invalid - dependent plugins may not function correctly!")
            0
        }
    }

    /**
     * The version of Kotlin provided by this copy of libkt.
     *
     * @since b1
     */
    val kotlinVersion: String by lazy {
        val unknownKotlinVersion = "UNKNOWN"
        try {
            val pluginDescriptor = YamlConfiguration.loadConfiguration(InputStreamReader(
                    this::class.java.getResourceAsStream("/plugin.yml")))
            val kotlinVersion = pluginDescriptor.getString("kotlinVersion", unknownKotlinVersion)
            if (kotlinVersion.isBlank()) unknownKotlinVersion else kotlinVersion
        } catch(e: Exception) {
            logger.warning("Could not detect Kotlin version provided.")
            unknownKotlinVersion
        }
    }

    override fun onEnable() {
        if (buildNumber > 0) {
            server.pluginManager.registerEvents(this, this)
        }
        logger.info("Providing version $kotlinVersion of Kotlin.");
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
                server.pluginManager.disablePlugin(plugin)
            }
        }
    }
}