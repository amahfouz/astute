package com.amahfouz.astute.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.amahfouz.astute.model.RecallGridModel

/**
 * View for grid cell.
 */
class CellView(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    var state : RecallGridModel.CellState? = RecallGridModel.CellState.EMPTY
        set(value) {

        }


    override  fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}