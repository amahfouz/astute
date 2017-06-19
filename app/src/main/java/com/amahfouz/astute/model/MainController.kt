package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * Primary controller for the whole app.
 *
 * Manages grid state and displayed message.
 */
class MainController(ui: GameUi) {

    var game = RecallGame(RecallGame.Config(ui, GameUi.Grid.Dims(3,5), 3, 10000))

}