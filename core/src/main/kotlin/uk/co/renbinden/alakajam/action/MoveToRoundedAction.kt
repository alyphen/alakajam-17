package uk.co.renbinden.alakajam.action

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions.action
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import kotlin.math.roundToInt

class MoveToRoundedAction private constructor() : MoveToAction() {

    private var endX: Float = 0f
    private var endY: Float = 0f

    override fun setPosition(x: Float, y: Float) {
        super.setPosition(x, y)
        this.endX = x
        this.endY = y
    }

    override fun setPosition(x: Float, y: Float, alignment: Int) {
        super.setPosition(x, y, alignment)
        this.endX = x
        this.endY = y
    }

    override fun update(percent: Float) {
        val x: Float
        val y: Float
        if (percent == 0f) {
            x = startX
            y = startY
        } else if (percent == 1f) {
            x = endX
            y = endY
        } else {
            x = (startX + (endX - startX) * percent).roundToInt().toFloat()
            y = (startY + (endY - startY) * percent).roundToInt().toFloat()
        }
        target.setPosition(x, y, alignment)
    }
}

fun moveToRounded(
    x: Float,
    y: Float,
    duration: Float = 0f,
    interpolation: Interpolation? = null
): MoveToRoundedAction {
    val action = action(MoveToRoundedAction::class.java)
    action.setPosition(x, y)
    action.duration = duration
    action.interpolation = interpolation
    return action
}