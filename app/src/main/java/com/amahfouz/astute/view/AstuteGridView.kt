package com.amahfouz.astute.view

import android.content.Context

import android.util.AttributeSet
import android.widget.GridView
import com.amahfouz.astute.model.RecallGridModel
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter

/**
 * Grid view showing the game board.
 * Gets its content from an RecallGridModel.
 */
class AstuteGridView @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0)
    : GridView(context, attrs, defStyleAttr), RecallGridModel.Provider.Listener {

    var cells: Array<CellView> = Array(0, { _ -> CellView(context)} )

    var model: RecallGridModel? = null
        get() = provider?.getGridModel()
        private set

    var provider: RecallGridModel.Provider? = null
        set(value) {
            field = value
            if (cells.size != value?.getGridModel()?.numCells)
                initCellsArray()
        }

    init {
        adapter = GridContentAdapter()
    }

    //
    // View
    //

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (model == null)
            return

        val dims = model?.dims ?: RecallGridModel.Dims(1, 1)

        numColumns = model?.dims?.cols ?: 0

        val numRows = dims?.rows
        val numCols = dims?.cols

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
    // RecallGridModel.Provider.Listener implementation
    //

    override fun modelChanged() {
        initCellsArray()
        numColumns = model?.dims?.cols ?: 0
    }

    override fun cellChanged(index: Int) {
        if (index < cells.size) {
            cells[index].state = model?.get(index)
        }
    }
    //
    // Private
    //

    private fun initCellsArray() {
        cells = Array(model?.numCells ?: 0, { _ -> CellView(context) } )
        cells.forEachIndexed{ index, cell -> cell.state = model?.get(index) }

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
        override fun getCount(): Int = model?.numCells ?: 0

        override fun getView(index: Int, recycledView: View?, p2: ViewGroup?): View {
            val result: CellView = cells[index]
            result.state = model?.get(index)
            result.layoutParams = AbsListView.LayoutParams(columnWidth, columnWidth)
            return result
        }
    }
}