package com.amahfouz.astute.model.api

import com.amahfouz.astute.model.CellState
import com.amahfouz.astute.model.RecallGameUi

/**
 * API between game model (Non-Android-specific code) and
 * all UI (Android-specific code)
 *
 * Specifies the UI of game as seen from the model and
 * the model as seen from the UI.
 */
interface GameUi {

    fun getMessage() : Message
    fun getGrid() : Grid
    fun getTimer() : Timer

    interface Message {

        fun set(msg: String)
    }

    interface Grid {

        fun updateCell(position: Int, state: CellState)
        fun setListener(l: Listener)

        interface Listener {
            fun handleClick(position: Int)
        }
    }

    interface Timer {

        fun schedule(delay: Long, r: Runnable) : Task

        interface Task {
            fun cancel(): Boolean
        }
    }
}