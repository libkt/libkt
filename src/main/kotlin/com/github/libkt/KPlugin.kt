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

import org.bukkit.plugin.Plugin

/**
 * A simple interface that allows libkt to disable this plugin should libkt not be up to date enough for this plugin
 * to function properly.
 *
 * @since v1
 */
interface KPlugin : Plugin {

    /**
     * The required libkt version number for this plugin to function properly.
     *
     * If the version of the running libkt plugin is not equal to or greater than the value of this property, this
     * plugin will be disabled.
     *
     * @since v1
     */
    val requiredLibktVersion: Int
}