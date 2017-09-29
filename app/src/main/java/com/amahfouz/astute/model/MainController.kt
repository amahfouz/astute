package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * Primary controller for the whole app.
 *
 * Manages grid state and displayed message.
 */
class MainController(private val ui: GameUi) : RecallGameState.Listener {

    private var game : RecallGameState? = null

    fun newGame() {
        // allow previous game to be GCed
        game?.listener = null
        game = RecallGameState(ui)
        game?.listener = this
    }

    //
    // RecallLevel.Listener
    //

    override fun levelEnded(message : String) {
        ui.getToast().show(message, "Continue", this::goNext)
    }

    //
    // private
    //

    private fun goNext() {
        // move to next level
        if (game!!.done) {
            val percent = 100 * game!!.totalScore / game!!.maxPossibleScore
            ui.getMessage().show("You scored $percent%")
        }
        else
            game!!.goToNextLevel()
    }
}