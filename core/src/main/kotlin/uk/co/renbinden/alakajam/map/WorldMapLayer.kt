package uk.co.renbinden.alakajam.map

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import kotlin.math.roundToInt

class WorldMapLayer(
    private val map: TiledMap,
    private val layer: MapLayer,
    batch: Batch,
    private val camera: OrthographicCamera
) : Actor(), ZYIndexed {

    private val tiledMapRenderer = OrthogonalTiledMapRenderer(map, batch)
    val z = layer.properties["z", Int::class.java] ?: 0
    override val zi = z
    override val yi = y.roundToInt()

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.end()
        tiledMapRenderer.setView(camera)
        tiledMapRenderer.batch.color = Color(1f, 1f, 1f, parentAlpha)
        tiledMapRenderer.render(intArrayOf(map.layers.indexOf(layer)))
        batch.begin()
    }

}