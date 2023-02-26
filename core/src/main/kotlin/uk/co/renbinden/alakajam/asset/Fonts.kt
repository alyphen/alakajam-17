package uk.co.renbinden.alakajam.asset

import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.g2d.BitmapFont

object Fonts {

    val lora32 = Asset(
        "fonts/lora32.fnt",
        BitmapFont::class.java,
        load = { assetManager ->
            val fontParameters = BitmapFontLoader.BitmapFontParameter()
            fontParameters.atlasName = "textures/textures.atlas"
            assetManager.load("fonts/lora32.fnt", BitmapFont::class.java, fontParameters)
        }
    )

    val lora16 = Asset(
        "fonts/lora16.fnt",
        BitmapFont::class.java,
        load = { assetManager ->
            val fontParameters = BitmapFontLoader.BitmapFontParameter()
            fontParameters.atlasName = "textures/textures.atlas"
            assetManager.load("fonts/lora16.fnt", BitmapFont::class.java, fontParameters)
        }
    )

}