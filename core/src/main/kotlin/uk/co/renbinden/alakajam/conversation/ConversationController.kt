package uk.co.renbinden.alakajam.conversation

import com.badlogic.gdx.scenes.scene2d.Stage
import com.kotcrab.vis.ui.widget.VisDialog
import uk.co.renbinden.alakajam.Alakajam17
import uk.co.renbinden.alakajam.actors.Player
import uk.co.renbinden.alakajam.inventory.ItemType

class ConversationController(
    private val game: Alakajam17,
    private val stage: Stage,
    private val model: ConversationModel,
    private val player: Player?
) {

    fun progress() {
        if (model.isFinished) {
            model.reset()
            player?.canMove = true
            return
        }
        if (model.story.canContinue()) {
            var isStatement = false
            val line = model.story.Continue()
            var title = ""
            var text = ""
            when {
                line.startsWith("ITEM") -> {
                    val params = line
                        .replace("\n", "")
                        .split(" ")
                        .drop(1)
                    val itemType = ItemType.valueOf(params[0])
                    val amount = params[1].toInt()
                    game.save.inventory.add(itemType, amount)
                    game.save.save()
                    isStatement = true
                }
                line.startsWith("SET_FLAG") -> {
                    val params = line.replace("\n", "")
                        .split(" ")
                        .drop(1)
                    val flag = ConversationFlag.valueOf(params[0])
                    val value = params[1].toBoolean()
                    game.save.conversationFlags[flag] = value
                    game.save.save()
                    isStatement = true
                }
                line.contains(": ") -> {
                    val parts = line.split(": ")
                    title = parts[0]
                    text = parts.drop(1).joinToString(": ")
                }
                else -> {
                    text = line
                }
            }
            val choices = if (model.story.currentChoices.isNotEmpty()) {
                model.story.currentChoices.mapIndexed { index, choice ->
                    ChoiceModel(model.story, index, choice.text)
                }
            } else {
                emptyList()
            }
            if (isStatement) {
                progress()
            } else {
                showNextDialog(title, text, *choices.toTypedArray())
            }
        }
    }

    fun showNextDialog(title: String, text: String, vararg choices: ChoiceModel): VisDialog {
        val dialog = ConversationDialog(title, text, this, *choices)
        stage.addActor(dialog.fadeIn())
        dialog.setPosition((stage.width - dialog.width) / 2f, ((stage.height - dialog.height) / 2f) + dialog.height)
        return dialog
    }

}