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