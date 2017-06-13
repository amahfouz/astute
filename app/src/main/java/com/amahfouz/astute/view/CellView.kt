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

    var state : CellState? = CellState.EMPTY
        set(newState) {
            val (resId, hasBackground) = when (newState) {
                CellState.EMPTY -> Pair(0, false)
                CellState.FILLED -> Pair(0, true)
                CellState.CORRECT -> Pair(R.drawable.check_mark, true)
                CellState.WRONG -> Pair(R.drawable.x, true)
                else -> {
                    Pair(0, false)
                }
            }

            setImageDrawable(if (resId > 0) resources.getDrawable(resId) else null)
            setBackgroundDrawable(if (hasBackground) resources.getDrawable(R.drawable.circle) else null)
        }

}