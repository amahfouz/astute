package com.amahfouz.astute.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView
import com.amahfouz.astute.R

/**
 * Provides grid content from grid model.
 */
class GridContentAdapter
    (val ctx: Context, val numCells: Int, val columnWidth: Int)
    : BaseAdapter() {

    //
    // BaseAdapter implementation
    //

    override fun getItem(p0: Int): Any = 0
    override fun getItemId(p0: Int): Long = 0
    override fun getCount(): Int = numCells

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val res = ctx.resources
        val shape = res.getDrawable(R.drawable.circle)
        val view = TextView(ctx)
        view.layoutParams = AbsListView.LayoutParams(columnWidth, columnWidth)
        view.setBackgroundDrawable(shape);
        return view
    }

}