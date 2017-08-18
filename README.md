# libkt
libkt is a simple Bukkit plugin whose main purpose is to make sure Kotlin and several other
common libraries are on the classpath for other plugins to use. It also provides an API for
allowing plugins to be automatically disabled if a recent enough version of libkt isn't
installed.

## How to use libkt
Simply add `depend: [libkt]` to your `plugin.yml`.

If you would like to libkt to automatically disable your plugin when the libkt build is not
recent enough simply implement [`KPlugin`](src/main/kotlin/com/github/libkt/KPlugin.kt) like
so:

```kotlin
class MyPlugin : JavaPlugin(), KPlugin {

    override val requiredLibktBuild = 1

}
```

## Dependency Information
```
maven repo url: http://repo.onarandombox.com/content/groups/public/
groupId: com.github.libkt
artifactId: libkt
version: 1-1.1
```