package com.amahfouz.astute.model

/**
 * Provides content for the grid.
 */
class RecallGridModel(val numCells: Int) {

    enum class CellState {
        EMPTY, FILLED, CORRECT, WRONG
    }

    var grid: Array<CellState> = Array(numCells, { CellState.EMPTY })

    fun get(index: Int): CellState = grid[index]

    fun set(x: Int, value: CellState) { grid[x] = value }

    fun clear() { grid.fill(CellState.EMPTY) }

    //
    // nested classes
    //

    interface Provider {

        fun getGridModel() : RecallGridModel

        fun setListener(listener: Listener)

        interface Listener {
            fun modelChanged()
            fun cellChanged(index: Int)
        }
    }
}