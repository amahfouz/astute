package com.amahfouz.astute.view

import android.content.Context
import android.content.res.Resources

import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import com.amahfouz.astute.R

/**
 * Grid view showing the main content of the app.
 */
class AstuteGridView @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0)
    : GridView(context, attrs, defStyleAttr) {


    init {
        adapter = Provider(context)
    }

    class Provider(val ctx: Context) : BaseAdapter() {

        override fun getItem(p0: Int): Any = 0
        override fun getItemId(p0: Int): Long = 0
        override fun getCount(): Int = 25

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//            val view = TextView(ctx)
//            view.text = "H"
            val res = ctx.resources
            val shape = res.getDrawable(R.drawable.circle)
            val view = TextView(ctx)
            view.text = "x"
            view.setBackgroundDrawable(shape);
            return view
        }

    }
}