package com.amahfouz.astute.model

/**
 * Primary controller for the whole app.
 *
 * Manages grid state and displayed message.
 */
class MainController(val ui: Game) : RecallGridModel.Provider {

    var mGridModel : RecallGridModel = RecallGridModel(RecallGridModel.Dims(3, 5))

    var mListener : RecallGridModel.Provider.Listener? = null

    var message : String = ""
        private set(value) {
            field = value
            ui?.messageChanged()
        }

    override fun getGridModel(): RecallGridModel = mGridModel

    override fun setListener(listener: RecallGridModel.Provider.Listener) {
        mListener = listener
    }

    //
    // Nested
    //

    interface Game {
        fun messageChanged();
    }
}