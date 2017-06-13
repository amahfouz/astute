package com.amahfouz.astute.model

/**
 * Main controller for the Recall game.
 */
class RecallGameController {

    enum class State {
        PREVIEW, SOLVE, IDLE
    }

    var gridSize: GridSize = GridSize.easiest()
    var state: State = State.IDLE;

    fun handleTap(cell: Int) {

    }

}