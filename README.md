# libkt
libkt is a simple Bukkit plugin whose main purpose is to make sure the version of Kotlin
you need is on the classpath. However, to avoid conflicts with other plugins that might
be doing the same thing, libkt takes some extra precautions. In addition, it will try to
provide some thoughtful extras to help Bukkit feel a little more Kotlin friendly.

Through a little bit of hackery with Bukkit's plugin system libkt is able to provide
multiple versions of the Kotlin standard library at once. This works by appending the
Kotlin major.minor version number to the plugin name. This way, a plugin can depend on
libkt for a specific version of Kotlin.

## How to use libkt
It is strongly recommended to check out the skeleton projects as they are set up to be as
automated as possible with regards to the hackery that libkt does.

[**Gradle Skeleton**](src/examples/gradle)

[**Maven Skeleton**](src/examples/maven)

Be aware that both of the build systems are set up to add information to a specially 
formatted [`plugin.yml`](src/examples/gradle/src/main/resources/plugin.yml) so don't forget
to include that!

### Doing it yourself
There are two main considerations when creating your build with libkt yourself.

##### plugin.yml
The libkt plugin name is based on the version of Kotlin that it includes. You must include
that in your depend value. Example: `depend: [libkt-1.1]`

##### Kotlin package relocation
You will need to relocate (via shade or shadow plugins) the Kotlin stdlib packages. You
have to do this even though you are not including the stdlib in your plugin. This because
the Kotlin compiler automatically includes some imports in your code and the namespaces
for those imports need to match what libkt provides. libkt sets up the Kotlin packages
similar to how CraftBukkit does it. Example: `kotlin.v1_1`

#### Dependency Information
```
maven repo url: http://repo.onarandombox.com/content/groups/public/
groupId: com.github.libkt
artifactId: libkt
version: 1.1-b1
```