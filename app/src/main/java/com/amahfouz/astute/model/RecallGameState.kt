package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * State of recall game.
 *
 * Holds current level, total score so far, and whether or not
 * the game has been completed.
 */
class RecallGameState(private val ui: GameUi) {

    // ID of ongoing level or the one to be started next
    var curLevelIndex: Int = 0
        private set

    var curLevelSpec: LevelSpec
        get() = LEVEL_SPECS[curLevelIndex]
        private set(v) {}

    var done : Boolean
        get() = curLevelIndex >= LEVEL_SPECS.size
        private set(v) {}

    var totalScore : Long = 0
        private set

    val maxPossibleScore : Int = LEVEL_SPECS.sumBy { LevelScore.calcMaxScore(it) }

    var listener: Listener? = null

    private var level = startNewLevel()

    //
    // public
    //

    interface Listener {
        fun levelEnded(message : String)
    }

    fun goToNextLevel() {
        if (! done) {
            level = startNewLevel()
        }
    }

    //
    // private
    //

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
            totalScore += score
            val percentage = score * 100 / LevelScore.calcMaxScore(curLevelSpec)
            val message = LevelScore.messageForPercentage(percentage.toInt() )
            curLevelIndex++
            listener?.levelEnded(message)
        }
    }
}