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
     * The version number of the libkt plugin which can be used to gauge which features are available in this plugin.
     *
     * @since v1
     */
    val versionNumber: Int by lazy {
        try {
            description.version.toInt()
        } catch(e: Exception) {
            logger.warning("libkt version number is invalid - dependent plugins may not function correctly!")
            0
        }
    }

    /**
     * The version of Kotlin provided by this copy of libkt.
     *
     * @since v1
     */
    val kotlinVersion: String by lazy {
        try {
            description.name.split(delimiters = "-", limit = 2)[1]
        } catch(e: Exception) {
            logger.warning("")
            "UNKNOWN"
        }
    }

    override fun onEnable() {
        if (versionNumber > 0) {
            registerEvents(this)
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
            if (versionNumber < plugin.requiredLibktVersion) {
                logger.severe("$plugin is being disabled as it requires v${plugin.requiredLibktVersion} or greater of" +
                        " libkt (found v$versionNumber)")
                plugin.setEnabled(false)
            }
        }
    }
}