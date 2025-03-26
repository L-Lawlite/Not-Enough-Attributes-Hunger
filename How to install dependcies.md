
# Add dependency in build.gradle
So in your `build.gradle`, you'll find blocks for `repositories {}` and `dependencies {}`. The latter should have the NeoForge version there already.
Adding any mod dependency (any kind, optional or required) consists of adding the mod file in `dependencies` and where to find it in `repositories`.
For Curios, you would have this in `repositories`:
``` 
repositories {
    maven {
        name = "Illusive Soulworks maven"
        url = "https://maven.theillusivec4.top/"
    }
}
```
Which specifies where Curios is located.
Then in `dependencies`:
```
dependencies {
    implementation("top.theillusivec4.curios:curios-neoforge:9.2.2+1.21.1"))
}
```
AppleSkin has their own corresponding info [here](https://github.com/squeek502/AppleSkin?tab=readme-ov-file#for-mod-developers):

Once you have this, you can refresh the project and you then should be able to use the mod's code.

## Making dependency optional

The only other thing involved is that, to make sure your dependency is optional, you need to make sure to only call your compatibility code when the mod is installed. This usually means doing something like:
```
if (ModList.get().isLoaded("curios")) {
  MyCompatibilityClass.doStuff();
}
```
So that you only access the compatibility code when the mod is installed and available.