package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

class Sprite2D(
    val boundary: Rectangle,
    val texture: Texture = Sprite.WHITE): Actor() {
    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch.draw(texture, boundary.x, boundary.y, boundary.width, boundary.height)
    }

    override fun act(delta: Float) {
        super.act(delta)
    }
}