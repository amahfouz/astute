package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * State of recall game.
 *
 * Holds current level, total score so far, and whether or not
 * the game has been completed.
 */
class RecallGameState(private val ui: GameUi) {

    var curLevelIndex: Int = 0
        private set

    var curLevelSpec: LevelSpec
        get() = LEVEL_SPECS[curLevelIndex]
        private set(v) {}

    var curScore: Int = 0
        private set

    var done : Boolean
        get() = curLevelIndex >= LEVEL_SPECS.size
        private set(v) {}

    var listener: Listener? = null

    private var level = startNewLevel()

    //
    // functions
    //

    interface Listener {
        fun levelEnded(score: Long)
    }

    fun goToNextLevel() {
        if (! done) {
            curLevelIndex++
            level = startNewLevel()
        }
    }

    private fun startNewLevel() : RecallLevel {

        // allow old level to be GCed
        level?.setListener(null)

        // start new level
        val newLevel = RecallLevel(ui, curLevelSpec)

        // register to get notified when game is over
        newLevel.setListener(LevelListener())

        return newLevel
    }

     inner class LevelListener : RecallLevel.Listener {

        override fun levelEnded(score: Long) {
            listener?.levelEnded(score = score)
        }
    }
}