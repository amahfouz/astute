package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GridDims

val dims3x3 = GridDims(3,3)
val dims3x4 = GridDims(3,4)
val dims4x6 = GridDims(4,6)
val dims5x7 = GridDims(5,7)
val dims6x9 = GridDims(6,9)

val _5sec = 5000L
val _3sec = 3000L


/**
 * Level configurations.
 * Each entry specifies board dimension and number of circles.
 */

private val levelConfig: Array<LevelSpec.Config> = arrayOf(
//    LevelSpec.Config(dims3x3, 2, _3sec, "You get 5 seconds to memorize which circles are filled, then you have to recall them!"),
    LevelSpec.Config(dims3x4, 4, _3sec, "Grids get larger, but back to 5 seconds for now!"),
//    LevelSpec.Config(dims3x4, 5, _5sec, "5 circles!"),
    LevelSpec.Config(dims3x4, 6, _3sec, "6 circles!"),
    LevelSpec.Config(dims4x6, 4, _3sec, null),
//    LevelSpec.Config(dims4x6, 5, _3sec, null),
    LevelSpec.Config(dims4x6, 6, _3sec, null),
    LevelSpec.Config(dims5x7, 5, _3sec, null),
    LevelSpec.Config(dims5x7, 7, _3sec, null),
    LevelSpec.Config(dims6x9, 5, _5sec, null),
    LevelSpec.Config(dims6x9, 7, _5sec, null)
)

val LEVEL_SPECS: List<LevelSpec> = levelConfig.mapIndexed() { index, config ->  LevelSpec(index + 1, config) }