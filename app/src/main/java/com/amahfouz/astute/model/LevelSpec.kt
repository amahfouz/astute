package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GridDims

/**
 * Spec of a level.
 */
class LevelSpec(val number: Int, val config: Config) {

    class Config(val dims: GridDims,
                 val numCircles: Int,
                 val previewTime: Long,
                 val instructions: String?) {

    }
}