package uk.co.renbinden.alakajam

import com.badlogic.gdx.Game
import uk.co.renbinden.alakajam.screen.MainScreen

class Alakajam17 : Game() {
    override fun create() {
        setScreen(MainScreen())
    }

    override fun dispose() {
        super.dispose()
    }
}