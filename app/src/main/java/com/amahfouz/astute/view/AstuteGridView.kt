package com.amahfouz.astute.view

import android.content.Context
import android.content.res.Resources
import android.text.Layout

import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import com.amahfouz.astute.R
import com.amahfouz.astute.model.RecallGridModel
import android.content.ContentProvider



/**
 * Grid view showing the main content of the app.
 */
class AstuteGridView @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0)
    : GridView(context, attrs, defStyleAttr) {


    var model: RecallGridModel? = null
        get() = this.model
        set(value) {
            getContentProvider()?.updateAll();
        }

    init {
        adapter = Provider(context)
    }

    //
    // Private
    //

    private fun getContentProvider(): ContentProvider?
         = adapter as? ContentProvider ?: null
}