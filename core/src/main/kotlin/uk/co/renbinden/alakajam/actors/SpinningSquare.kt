package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions

class SpinningSquare(sprite: Sprite) : GameObject(sprite) {
    init {
        growAndRotate()
    }

    private fun growAndRotate() {
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.rotateBy(-360f, 4f),
                    Actions.scaleTo(0.5f, 0.5f, 4f, Interpolation.sine)
                ),
                Actions.run { shrinkAndRotate() }
            )
        )
    }

    private fun shrinkAndRotate() {
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.rotateBy(-360f, 4f),
                    Actions.scaleTo(0.2f, 0.2f, 4f, Interpolation.sine)
                ),
                Actions.run { growAndRotate() }
            )
        )
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }
}