package com.amahfouz.astute.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView
import com.amahfouz.astute.R
import com.amahfouz.astute.model.RecallGridModel

/**
 * Provides GridView cell content from a RecallGridModel.Provider.
 */
class GridContentAdapter(val ctx: Context,
                         val provider: RecallGridModel.Provider) : BaseAdapter() {

    //
    // BaseAdapter implementation
    //

    override fun getItem(p0: Int): Any = 0
    override fun getItemId(p0: Int): Long = 0
    override fun getCount(): Int = provider.model.numCells

    override fun getView(index: Int, recycledView: View?, p2: ViewGroup?): View {
        val res = ctx.resources
        val shape = res.getDrawable(R.drawable.circle)
        val view = TextView(ctx)
        val colWidth = provider.model.columnWidth
        view.layoutParams = AbsListView.LayoutParams(colWidth, colWidth)
        if (provider.model.get)
        view.setBackgroundDrawable(shape);
        return view
    }

}