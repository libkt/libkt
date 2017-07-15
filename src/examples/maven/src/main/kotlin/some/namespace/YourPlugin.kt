package some.namespace

import com.github.libkt.KPlugin
import org.bukkit.plugin.java.JavaPlugin

// KPlugin is optional. It causes your plugin to disable if the build of libkt being used isn't sufficient for your
// plugin to operate normally.
class YourPlugin : JavaPlugin(), KPlugin {

    override fun getRequiredLibktBuild() = 1

    override fun onEnable() {
        println("Hello World!")
    }
}