package com.amahfouz.astute.model

/**
 * A cell can be in one of four states as specified by
 * the two booleans held here.
 */
class CellState (val filled: Boolean = false, val correct: Match = CellState.Match.NONE){

    enum class Match {
        CORRECT, WRONG, NONE
    }
}