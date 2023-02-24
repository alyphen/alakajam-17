package uk.co.renbinden.alakajam

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setWindowedMode(1280, 720)
    //config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode())
    config.setForegroundFPS(60)
    config.setTitle("Alakajam 17")
    Lwjgl3Application(Alakajam17(), config)
}