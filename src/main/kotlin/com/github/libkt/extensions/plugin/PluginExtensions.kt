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
/**
 * Extension functions and properties that pertain to [Plugin].
 */
package com.github.libkt.extensions.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

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