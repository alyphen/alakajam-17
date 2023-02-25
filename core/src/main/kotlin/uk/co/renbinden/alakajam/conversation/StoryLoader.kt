package uk.co.renbinden.alakajam.conversation

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.files.FileHandle
import com.bladecoder.ink.runtime.Story
import com.badlogic.gdx.utils.Array as GdxArray

class StoryLoader(resolver: FileHandleResolver) : AsynchronousAssetLoader<Story, StoryLoader.Parameters>(resolver) {

    class Parameters : AssetLoaderParameters<Story>()

    private var story: Story? = null

    override fun loadSync(manager: AssetManager, fileName: String, file: FileHandle, parameter: Parameters?): Story? {
        return story
    }

    override fun getDependencies(fileName: String, file: FileHandle, parameter: Parameters?): GdxArray<AssetDescriptor<Any>> {
        return GdxArray()
    }

    override fun loadAsync(manager: AssetManager, fileName: String, file: FileHandle, parameter: Parameters?) {
        story = Story(file.readString(Charsets.UTF_8.name()).replace('\uFEFF', ' '))
    }
}