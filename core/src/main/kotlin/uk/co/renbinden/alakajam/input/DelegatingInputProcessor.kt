package uk.co.renbinden.alakajam.input

import com.badlogic.gdx.InputProcessor

class DelegatingInputProcessor(private vararg val delegates: InputProcessor): InputProcessor {
    override fun keyDown(keycode: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.keyDown(keycode)) return true
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.keyUp(keycode)) return true
        }
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        for (delegate in delegates) {
            if (delegate.keyTyped(character)) return true
        }
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.touchDown(screenX, screenY, pointer, button)) return true
        }
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.touchUp(screenX, screenY, pointer, button)) return true
        }
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.touchDragged(screenX, screenY, pointer)) return true
        }
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        for (delegate in delegates) {
            if (delegate.mouseMoved(screenX, screenY)) return true
        }
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        for (delegate in delegates) {
            if (delegate.scrolled(amountX, amountY)) return true
        }
        return false
    }
}