package uk.co.renbinden.alakajam.save

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import uk.co.renbinden.alakajam.conversation.ConversationFlag
import uk.co.renbinden.alakajam.inventory.Inventory

class Save {

    val version = CURRENT_SAVE_VERSION
    val inventory = Inventory()
    val mapState = mutableMapOf<String, Map<Int, Map<String, Any>>>()
    val playerPosition = PlayerPosition()
    val conversationFlags = mutableMapOf<ConversationFlag, Boolean>()

    companion object {
        const val CURRENT_SAVE_VERSION = 1
        fun load(file: FileHandle = Gdx.files.local("save.json")): Save? {
            Gdx.app.log(Save::class.java.simpleName, "Loading from ${file.path()}...")
            if (!file.exists()) return null
            val gson = Gson()
            val save = gson.fromJson(file.readString(), Save::class.java)
            save.update()
            Gdx.app.log(Save::class.java.simpleName, "Loaded.")
            return save
        }
    }

    fun save(file: FileHandle = Gdx.files.local("save.json")) {
        Gdx.app.log(javaClass.simpleName, "Saving to ${file.path()}...")
        val gson = GsonBuilder().setPrettyPrinting().create()
        file.writeString(gson.toJson(this), false)
        Gdx.app.log(javaClass.simpleName, "Saved.")
    }

    fun update() {
        if (version > CURRENT_SAVE_VERSION) {
            Gdx.app.log(javaClass.simpleName, "Save is from a more recent version of the game (save version $version, current version $CURRENT_SAVE_VERSION)")
        }
        if (version < CURRENT_SAVE_VERSION) {
            Gdx.app.log(javaClass.simpleName, "Save is from an older version of the game, updating... (save version $version, current version $CURRENT_SAVE_VERSION)")
        }
    }

}