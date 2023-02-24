package uk.co.renbinden.alakajam.architecture

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

class Stage(viewport: Viewport) : Stage(viewport) {
    fun middle(): Vector2 {
        return Vector2(
            super.getWidth() / 2,
            super.getHeight() / 2
        )
    }

    fun middleX(): Float {
        return super.getWidth() / 2
    }

    fun middleY(): Float {
        return super.getHeight() / 2
    }


}