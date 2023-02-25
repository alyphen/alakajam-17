package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.Gdx.app
import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import uk.co.renbinden.alakajam.action.moveToRounded
import uk.co.renbinden.alakajam.actors.Player.PlayerState.BOATING
import uk.co.renbinden.alakajam.actors.Player.PlayerState.WALKING
import uk.co.renbinden.alakajam.behaviour.Collidable
import uk.co.renbinden.alakajam.behaviour.Interactable
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import uk.co.renbinden.alakajam.controls.Controls
import uk.co.renbinden.alakajam.direction.Direction.*
import uk.co.renbinden.alakajam.map.InvalidMapException
import uk.co.renbinden.alakajam.screen.MapScreen
import kotlin.math.roundToInt

class Player(
    private val walkUpAnimation: Animation<out TextureRegion>,
    private val walkDownAnimation: Animation<out TextureRegion>,
    private val walkLeftAnimation: Animation<out TextureRegion>,
    private val walkRightAnimation: Animation<out TextureRegion>,
    private val boatTexture: TextureRegion
) : Actor(), ZYIndexed {

    companion object {
        fun createPlayer(
            screen: MapScreen,
            stage: Stage,
            mapObject: MapObject,
            z: Int,
            textureAtlas: TextureAtlas
        ): Player {
            val id = mapObject.properties["id", Int::class.java]
            val x = mapObject.properties["x", Float::class.java]
                ?: throw InvalidMapException("Player $id does not have an x attribute set")
            val y = mapObject.properties["y", Float::class.java]
                ?: throw InvalidMapException("Player $id does not have a y attribute set")
            val width = mapObject.properties["width", Float::class.java]
                ?: throw InvalidMapException("Player $id does not have a width attribute set")
            val height = mapObject.properties["height", Float::class.java]
                ?: throw InvalidMapException("Player $id does not have a height attribute set")
            return createPlayer(
                screen,
                stage,
                textureAtlas,
                x,
                y,
                z,
                width,
                height
            )
        }

        fun createPlayer(
            screen: MapScreen,
            stage: Stage,
            textureAtlas: TextureAtlas,
            x: Float,
            y: Float,
            z: Int,
            width: Float = 16f,
            height: Float = 16f
        ): Player {
            if (screen.player != null) {
                throw InvalidMapException("Multiple player objects")
            }
            val playerMoveUpAnimation = Animation(
                0.2f,
                textureAtlas.findRegions("player_northwalk"),
                Animation.PlayMode.LOOP
            )
            val playerMoveDownAnimation = Animation(
                0.2f,
                textureAtlas.findRegions("player_southwalk"),
                Animation.PlayMode.LOOP
            )
            val playerMoveLeftAnimation = Animation(
                0.2f,
                textureAtlas.findRegions("player_westwalk"),
                Animation.PlayMode.LOOP
            )
            val playerMoveRightAnimation = Animation(
                0.2f,
                textureAtlas.findRegions("player_eastwalk"),
                Animation.PlayMode.LOOP
            )
            val boatTexture = textureAtlas.findRegion("boat")
            val player = Player(
                playerMoveUpAnimation,
                playerMoveDownAnimation,
                playerMoveLeftAnimation,
                playerMoveRightAnimation,
                boatTexture
            )
            player.x = x
            player.y = y
            player.z = z
            player.width = width
            player.height = height
            stage.addActor(player)
            screen.player = player
            stage.keyboardFocus = player
            return player
        }
    }

    private var animation = walkDownAnimation
    var z: Int = 0

    override val zi
        get() = z
    override val yi
        get() = y.roundToInt()

    private enum class PlayerState {
        WALKING,
        BOATING
    }

    private var stateTime = 0f
    private var hasJustCollided = false
    private var direction = DOWN
    var canMove = true
    private var animationPaused = true
    private var state: PlayerState = WALKING

    init {
        addListener(object : InputListener() {
            override fun keyDown(event: InputEvent, keycode: Int): Boolean {
                if (keycode in Controls.interact) {
                    if (canMove) {
                        val actorsAtPosition = stage.actors.filter { actor ->
                            actor != this@Player
                                    && actor is Interactable
                                    && x + direction.dx < actor.x + actor.width
                                    && x + direction.dx + width > actor.x
                                    && y + direction.dy < actor.y + actor.height
                                    && y + direction.dy + height > actor.y
                                    && actor is ZYIndexed
                                    && zi == actor.zi
                        }
                        actorsAtPosition.forEach { actor -> (actor as Interactable).interact() }
                    }
                    return true
                }
                return false
            }
        })
    }

    override fun act(delta: Float) {
        super.act(delta)
        move()
        animate(delta)
    }

    private fun move() {
        if (canMove && actions.isEmpty) {
            var vertical = 0
            var horizontal = 0
            if (Controls.up.any(input::isKeyJustPressed)
                || Controls.left.any(input::isKeyJustPressed)
                || Controls.down.any(input::isKeyJustPressed)
                || Controls.right.any(input::isKeyJustPressed)) {
                hasJustCollided = false
            }
            if (Controls.up.any(input::isKeyPressed)) {
                vertical += 1
            }
            if (Controls.left.any(input::isKeyPressed)) {
                horizontal -= 1
            }
            if (Controls.down.any(input::isKeyPressed)) {
                vertical -= 1
            }
            if (Controls.right.any(input::isKeyPressed)) {
                horizontal += 1
            }
            when {
                vertical > 0 -> {
                    moveIfFree(x, y + 16)
                    direction = UP
                    animation = walkUpAnimation
                }
                vertical < 0 -> {
                    moveIfFree(x, y - 16)
                    direction = DOWN
                    animation = walkDownAnimation
                }
                horizontal > 0 -> {
                    moveIfFree(x + 16, y)
                    direction = RIGHT
                    animation = walkRightAnimation
                }
                horizontal < 0 -> {
                    moveIfFree(x - 16, y)
                    direction = LEFT
                    animation = walkLeftAnimation
                }
                else -> {
                    animationPaused = true
                    stateTime = 0f
                }
            }
        }
    }

    private fun moveIfFree(x: Float, y: Float) {
        val actorsAtPosition = stage.actors
            .filter { actor ->
                actor != this
                        && x < actor.x + actor.width
                        && x + width > actor.x
                        && y < actor.y + actor.height
                        && y + height > actor.y
                        && actor is ZYIndexed
                        && zi == actor.zi
            }
        val collidableActorsAtPosition = actorsAtPosition
            .filter { actor ->
                actor is Collidable
                        && actor.isSolid
            }
        if (collidableActorsAtPosition.isEmpty()) {
            if (actorsAtPosition.any { it is Water }) {
                state = BOATING
                animationPaused = true
                stateTime = 0f
            } else {
                state = WALKING
                animationPaused = false
            }
            addAction(moveToRounded(x, y, 0.2f))
            val vertPointsAtPosition = stage.actors.filterIsInstance<VertPoint>()
                .filter { actor ->
                    x < actor.x + actor.width
                            && x + width > actor.x
                            && y < actor.y + actor.height
                            && y + height > actor.y
                            && actor.zi == zi
                }
            if (vertPointsAtPosition.size > 1) {
                app.error(javaClass.simpleName, "Overlapping with two vert points at $x, $y, do not do this!!")
                app.exit()
                return
            }
            val vertPoint = vertPointsAtPosition.singleOrNull()
            if (vertPoint != null) {
                z = vertPoint.targetZ
            }
        } else {
            if (!hasJustCollided) {
                collidableActorsAtPosition.forEach { actor -> (actor as Collidable).collide() }
            }
            hasJustCollided = true
        }
    }

    private fun animate(delta: Float) {
        if (!animationPaused) {
            stateTime += delta
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.color = Color(1f, 1f, 1f, parentAlpha)
        if (state == BOATING) {
            batch.draw(boatTexture, x - 48, y - 32)
        }
        batch.draw(animation.getKeyFrame(stateTime), x, y)
    }

}