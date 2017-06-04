package com.amahfouz.astute.model


class RecallGridModel(size: Int) {

    var grid: Array<BooleanArray>
        = Array(size, { BooleanArray(size) })

    fun get(x: Int, y: Int) { grid[x][y] }

    fun set(x: Int, y: Int) { grid[x][y] = true }

    fun clear() { grid.forEach { it.fill(false) } }

    //
    // nested classes
    //

    class Provider(var model: RecallGridModel)
}