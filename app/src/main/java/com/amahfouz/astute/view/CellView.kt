package com.amahfouz.astute.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.amahfouz.astute.R
import com.amahfouz.astute.model.CellState

/**
 * View for grid cell.
 */
class CellView(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : ImageView(context, attrs, defStyleAttr, defStyleRes) {

    var state : CellState = CellState()
        set(newState) {
            field = newState
            val resId = when (newState.match) {
                CellState.Match.CORRECT -> R.drawable.check_mark
                CellState.Match.WRONG -> R.drawable.x
                else -> {
                    -1
                }
            }

            setImageDrawable(if (resId >= 0) resources.getDrawable(resId) else null)
            val backgroundResId = if (newState.filled) R.drawable.filled_circle else R.drawable.circle
            setBackgroundDrawable(resources.getDrawable(backgroundResId))

            invalidate()
        }

}