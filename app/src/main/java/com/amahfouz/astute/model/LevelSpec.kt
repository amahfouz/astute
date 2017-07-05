package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GridDims

/**
 * Created by amahfouz on 7/3/17.
 */
class LevelSpec(val number: Int, val config: Config) {

    class Config(val dims: GridDims,
                 val numCircles: Int,
                 val previewTime: Long,
                 val instructions: String?) {

    }
}