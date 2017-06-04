package com.amahfouz.astute.model

/**
 *
 */
class MainController(val ui: Game) {

    var recallController: RecallGameController = RecallGameController()

    fun newGame() {
        recallController = RecallGameController()
    }

    //
    // Nested
    //

    interface Game {
        fun recallControllerChanged();
    }
}