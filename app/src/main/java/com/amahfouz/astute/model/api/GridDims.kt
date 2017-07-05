package com.amahfouz.astute.model.api

/**
 * Width and height of a grid in cells.
 */
class GridDims(val cols: Int, val rows: Int) {
    val size = cols * rows;
}
