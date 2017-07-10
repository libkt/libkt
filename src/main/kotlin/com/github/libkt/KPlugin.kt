package com.github.libkt

import org.bukkit.plugin.Plugin

/**
 * A simple interface that allows libkt to disable this plugin should libkt not be up to date enough for this plugin
 * to function properly.
 *
 * @since b1
 */
interface KPlugin : Plugin {

    /**
     * The required libkt build number for this plugin to function properly.
     *
     * If the build number of the running libkt plugin is not equal to or greater than the value of this property, this
     * plugin will be disabled.
     *
     * @since b1
     */
    val requiredLibktBuild: Int
}