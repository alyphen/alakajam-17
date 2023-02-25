package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Actor

class Text(private val font: BitmapFont, private val text: String) : Actor() {

    override fun draw(batch: Batch, parentAlpha: Float) {
        font.draw(batch, text, x, y)
    }

}