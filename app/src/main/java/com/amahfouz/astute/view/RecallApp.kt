package com.amahfouz.astute.view

import android.app.Application
import com.amahfouz.astute.model.RecallGameController

/**
 * Extends application to store global state.
 *
 * Used to re-init state when main activity gets destroyed.
 */
class RecallApp: Application() {

    companion object {
        var mainController: RecallGameController? = null

    }
}