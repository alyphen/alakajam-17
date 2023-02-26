package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class GameObject(var sprite: Sprite? = null) : Actor() {

    init {
        updateSprite()
    }

    override fun act(delta: Float) {
        super.act(delta)
        updateSprite()
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        sprite?.draw(batch)
    }

    private fun updateSprite() {
        sprite?.rotation = rotation
        sprite?.setScale(scaleX, scaleY)
        sprite?.setOriginBasedPosition(x, y)
    }

}