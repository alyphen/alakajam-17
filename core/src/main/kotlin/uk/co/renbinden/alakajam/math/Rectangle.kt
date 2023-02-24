package uk.co.renbinden.alakajam.math

object Rectangle{
    operator fun invoke(x: Int, y: Int, width: Int, height: Int): com.badlogic.gdx.math.Rectangle{
        return com.badlogic.gdx.math.Rectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
    }
    operator fun invoke(x: Double, y: Double, width: Double, height: Double): com.badlogic.gdx.math.Rectangle{
        return com.badlogic.gdx.math.Rectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
    }
    operator fun invoke(x: Float, y: Float, width: Float, height: Float): com.badlogic.gdx.math.Rectangle{
        return com.badlogic.gdx.math.Rectangle(x, y, width, height)
    }
}

