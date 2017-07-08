/*
 * This file is part of libkt.
 *
 * Libkt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libkt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2017 libkt team
 */
package com.github.libkt

import org.bstats.Metrics
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main plugin class for libkt. Not very exciting right now.
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