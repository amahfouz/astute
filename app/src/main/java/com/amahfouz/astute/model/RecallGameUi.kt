package com.amahfouz.astute.model

/**
 * Game UI as seen by the controller.
 */
interface RecallGameUi {

    fun reset()

    interface Grid {
        fun clear()
    }
}