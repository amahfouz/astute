package com.amahfouz.astute.model

/**
 * Main controller for the Recall game.
 */
class RecallGameController {

    var gridModel = RecallGridModel()

    enum class State {
        PREVIEW, SOLVE, IDLE
    }

    var gridSize: GridSize = GridSize.easiest()
    var state: State = State.IDLE;

    fun handleTap(cell: Int) {

    }

}