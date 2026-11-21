Custom GUI Scale (Fabric, MC 1.21.11)
Build: install JDK 21 + Gradle (or copy gradlew/gradle/ from a template at fabricmc.net/develop), then:
  gradle build      -> jar in build/libs/guiscale-1.0.0.jar
Put the jar in .minecraft/mods with Fabric Loader. Set GUI Scale to 4 (or Auto).
If 4 isn't selectable: close the game, set "guiScale:4" in options.txt.
Edit TARGET_GUI_WIDTH in WindowMixin.java to change the size.
