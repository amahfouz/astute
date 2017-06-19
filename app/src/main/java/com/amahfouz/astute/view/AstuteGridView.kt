package com.amahfouz.astute.view

import android.content.Context

import android.util.AttributeSet
import android.widget.GridView
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.AdapterView
import com.amahfouz.astute.model.CellState
import com.amahfouz.astute.model.api.GameUi

/**
 * Grid view showing the game board.
 * Gets its content from an RecallGridModel.
 */
class AstuteGridView @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0)
    : GridView(context, attrs, defStyleAttr), GameUi.Grid {

    private var cells: Array<CellView> = Array(0, { _ -> CellView(context)} )
    private var _listener : GameUi.Grid.Listener? = null
    private var _dims : GameUi.Grid.Dims = GameUi.Grid.Dims(1, 1)

    init {
        adapter = GridContentAdapter()
        onItemClickListener = AdapterView.OnItemClickListener {
            _, _, position, _ ->
            _listener?.handleClick(position)
        }

    }

    //
    // GameUi.Grid implementation
    //

    override fun resize(dims: GameUi.Grid.Dims) {
        if (dims.equals(_dims))
            return

        _dims = dims
        numColumns = dims.size

        cells = Array(_dims.size, { _ -> CellView(context) } )

        invalidate()
    }

    override fun fill(state: CellState) {
        cells.forEach { cell -> cell.state = state }
    }

    override fun updateCell(position: Int, state: CellState) {
        cells[position].state = state
    }

    override fun setListener(l: GameUi.Grid.Listener) {
        _listener = l
    }

    //
    // View
    //

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        numColumns = _dims?.cols

        val numRows = _dims?.rows
        val numCols = _dims?.cols

        val maxCellHeight = height / numRows
        val maxCellWidth = width / numCols

        if (maxCellWidth > maxCellHeight) {
            verticalSpacing = 10
            val availRowHeight = height - (numRows - 1) * verticalSpacing
            val rowHeight = availRowHeight / numRows
            val colWidth = rowHeight
            horizontalSpacing = (width - (colWidth * numCols)) / (numCols - 1)
            columnWidth = colWidth
        }
        else {
            horizontalSpacing = 10
            val availColWidth = width - (numCols - 1) * horizontalSpacing
            val colWidth = availColWidth / numCols
            val rowHeight = colWidth
            verticalSpacing = (height - (rowHeight * numRows)) / (numRows - 1)
            columnWidth = colWidth
        }
    }

    //
    // Inner
    //

    /**
     * Provides GridView cell content from a RecallGridModel.Provider.
     */
    inner class GridContentAdapter() : BaseAdapter() {

        //
        // BaseAdapter implementation
        //

        override fun getItem(p0: Int): Any = 0
        override fun getItemId(p0: Int): Long = 0
        override fun getCount(): Int = cells.size

        override fun getView(index: Int, recycledView: View?, p2: ViewGroup?): View {
            val result: CellView = cells[index]
            result.layoutParams = AbsListView.LayoutParams(columnWidth, columnWidth)
            return result
        }
    }
}