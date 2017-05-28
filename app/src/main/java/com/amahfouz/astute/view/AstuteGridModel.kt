package com.amahfouz.astute.view


class AstuteGridModel(size: Int) {

    var grid: Array<BooleanArray>
        = Array(size, { BooleanArray(size) })

    fun get(x: Int, y: Int) { grid[x][y] }

    fun set(x: Int, y: Int) { grid[x][y] = true }

    fun clear() { grid.forEach { it.fill(false) } }
}