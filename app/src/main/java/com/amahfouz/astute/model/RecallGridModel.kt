package com.amahfouz.astute.model

/**
 * Provides content for the grid.
 */
class RecallGridModel(size: Int) {

    val numCells: Int = size
    var listener


    var grid: Array<BooleanArray>
        = Array(size, { BooleanArray(size) })

    fun get(index: Int) =

    fun get(x: Int, y: Int) = grid[x][y]

    fun set(x: Int, y: Int) { grid[x][y] = true }

    fun clear() { grid.forEach { it.fill(false) } }

    //
    // nested classes
    //

    interface Provider {

        fun getGridModel() : RecallGridModel

        fun setListener(listener: Listener)

        interface Listener {
            fun cellChanged(index: Int)
        }
    }
}