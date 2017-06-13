package com.amahfouz.astute.model

/**
 * Provides content for the grid.
 */
class RecallGridModel(val dims: Dims) {

    var numCells: Int = 0
        get() = dims.size

    var grid: Array<CellState> = Array(numCells, { CellState.FILLED })

    fun get(index: Int): CellState = grid[index]

    fun set(x: Int, value: CellState) { grid[x] = value }

    fun clear() { grid.fill(CellState.EMPTY) }

    //
    // nested classes
    //

    class Dims(val width: Int, val height: Int) {
        val size = width * height;
    }

    interface Provider {

        fun getGridModel() : RecallGridModel

        fun setListener(listener: Listener)

        interface Listener {
            fun modelChanged()
            fun cellChanged(index: Int)
        }
    }
}