package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import uk.co.renbinden.alakajam.inventory.ItemType

class ItemPopup(
    stage: Stage,
    private val itemType: ItemType,
    private val amount: Int,
    private val font: BitmapFont
) : Actor() {

    private val text = if (amount == 1) {
        "Found ${itemType.displayName}"
    } else {
        "Found $amount x ${itemType.displayName}"
    }
    private val glyphLayout = GlyphLayout(font, text)

    init {
        this.x = (stage.width - glyphLayout.width) / 2
        this.y = ((stage.height - glyphLayout.height) / 2) + 64
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.moveTo(x, y + 32, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            )
        )
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        font.color.a = color.a * parentAlpha
        glyphLayout.setText(font, text)
        font.draw(batch, glyphLayout, x, y)
    }

}