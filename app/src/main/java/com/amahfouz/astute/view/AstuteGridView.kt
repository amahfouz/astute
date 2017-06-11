package com.amahfouz.astute.view

import android.content.Context

import android.util.AttributeSet
import android.widget.GridView
import com.amahfouz.astute.model.RecallGridModel
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView
import com.amahfouz.astute.R

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
        get() = this.provider
        set(value) {
            field = value
            if (cells.size != value?.getGridModel()?.numCells)
                initCellsArray()
        }

    init {
        adapter = GridContentAdapter()
    }

    //
    // RecallGridModel.Provider.Listener implementation
    //

    override fun modelChanged() {
        handleModelChanged()
    }

    override fun cellChanged(index: Int) {
        if (index < cells.size) {
            cells[index].state = model?.get(index)
        }
    }
    //
    // Private
    //

//    private fun getContentProvider(): GridContentAdapter?
//         = adapter as? GridContentAdapter ?: null

    private fun handleModelChanged() {
        initCellsArray()
    }

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
            val res = context.resources
            val shape = res.getDrawable(R.drawable.circle)
            val view = TextView(context)
            val colWidth = columnWidth
            view.layoutParams = AbsListView.LayoutParams(colWidth, colWidth)
            if (getModel()?.get(index) ?: false)
                view.setBackgroundDrawable(shape);
            return view
        }

    }
}