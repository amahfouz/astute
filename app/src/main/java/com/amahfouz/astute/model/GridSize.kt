package com.amahfouz.astute.model

/**
 * Enumeration of grid sizes.
 */
class GridSize(val rows: Int, val cols: Int, val description: String) {

    companion object {

        val sizes: Array<GridSize> = arrayOf(
            GridSize(4, 3, "Small"),
            GridSize(6, 4, "Medium"),
            GridSize(7, 5, "Large"),
            GridSize(8, 6, "Larger")
        )

        fun sizes() = sizes
        fun easiest() = sizes[0]
    }
}