package com.amahfouz.astute.view

import android.content.Context

import android.util.AttributeSet
import android.widget.GridView
import com.amahfouz.astute.model.RecallGridModel
import android.content.ContentProvider
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
    : GridView(context, attrs, defStyleAttr) {

    var provider: RecallGridModel.Provider?
        get() = this.provider
        set(value) {
            getContentProvider()?.updateAll();
        }

    init {
        adapter = GridContentAdapter()
    }

    //
    // Private
    //

    private fun getContentProvider(): GridContentAdapter?
         = adapter as? GridContentAdapter ?: null

    private fun getModel(): RecallGridModel? = provider?.getGridModel()

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
        override fun getCount(): Int = provider.getGridModel().numCells

        override fun getView(index: Int, recycledView: View?, p2: ViewGroup?): View {
            val res = context.resources
            val shape = res.getDrawable(R.drawable.circle)
            val view = TextView(context)
            val colWidth = provider.model.columnWidth
            view.layoutParams = AbsListView.LayoutParams(colWidth, colWidth)
            if (provider.model.get)
                view.setBackgroundDrawable(shape);
            return view
        }

    }
}