package com.amahfouz.astute.view

import android.content.Context

import android.util.AttributeSet
import android.widget.GridView
import com.amahfouz.astute.model.RecallGridModel
import android.content.ContentProvider

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
        adapter = Provider(context)
    }

    //
    // Private
    //

    private fun getContentProvider(): ContentProvider?
         = adapter as? ContentProvider ?: null
}