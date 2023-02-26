package uk.co.renbinden.alakajam.screen.container

import com.badlogic.gdx.Screen
import uk.co.renbinden.alakajam.Alakajam17
import uk.co.renbinden.alakajam.screen.LoadingScreen

class ScreenContainer(private val game: Alakajam17) {

    fun switchScreen(disposeOldScreen: Boolean = true, screenInit: () -> Screen) {
        val loadingScreen = LoadingScreen(game)
        loadingScreen.onLoad = {
            val screen = screenInit.invoke()
            game.screen = screen
            loadingScreen.dispose()
        }
        val oldScreen = game.screen
        game.screen = loadingScreen
        if (disposeOldScreen) {
            oldScreen?.dispose()
        }
    }

}