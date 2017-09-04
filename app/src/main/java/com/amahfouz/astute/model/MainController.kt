package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * Primary controller for the whole app.
 *
 * Manages grid state and displayed message.
 */
class MainController(val ui: GameUi) : RecallGame.Listener {

    var curLevelIndex: Int = 0
    var game = startNewLevel()

    //
    // RecallGame.Listener
    //

    override fun gameEnded(isWin: Boolean) {
        ui.getPopup().show("Good", "Continue", this::goNext)
    }

    //
    // private
    //

    private fun goNext() {
        // move to next level
        curLevelIndex++
        if (curLevelIndex < LEVEL_SPECS.size)
            game = startNewLevel()
    }

    private fun startNewLevel() : RecallGame {

        // allow old game to be GCed
        game?.setListener(null)

        // start new level
        val newGame = RecallGame(ui, LEVEL_SPECS[curLevelIndex])

        // register to get notified when game is over
        newGame.setListener(this)

        return newGame
    }
}