package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

class Sprite2D(val sprite: Sprite, val position: Vector2, val size: Vector2) : Actor() {

    init {
        sprite.setPosition(position.x, position.y)
        sprite.setSize(size.x, size.y)
        sprite.setOriginCenter()
    }

    var t = 0.0

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        sprite.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        t += delta
        sprite.rotate(90 * delta)
        sprite.setScale(6f + 5f * Math.sin(t).toFloat())
    }
}