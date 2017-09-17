package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * State of a recall game.
 *
 * The game can be in two modes, preview and solve.
 */
class RecallGame(val ui: GameUi,
                 val level: LevelSpec) : GameUi.Grid.Listener {

    // grid UI
    private val grid : GameUi.Grid
        get() = ui.getGrid()

    // message UI
    private val message : GameUi.Message
        get() = ui.getMessage()

    // cells randomly chosen as the puzzle code
    private val solution = RandomSet(level.config.numCircles,
                                     level.config.dims.size - 1)

    // cells user have selected so far in SOLVE mode
    private var selected : MutableList<Int> = mutableListOf()

    // cur state of the game
    private var state : State = Preview()

    // listens to game over event
    private  var listener: Listener? = null

    init {
        ui.getGrid().setListener(this)
    }

    //
    // public
    //

    fun setListener(l: Listener?) {
        this.listener = l
    }

    //
    // GameUi.Grid.Listener implementation
    //

    override fun handleClick(position: Int) {
        state.handleSelect(position)
    }

    //
    // nested and inner
    //

    interface Listener {
        fun gameEnded(isWin: Boolean)
    }

    interface State {
        fun handleSelect(position: Int)
    }

    inner class Preview : State {

        constructor() {
            grid.resize(level.config.dims)
            grid.fill(CellState())
            solution.forEach { pos -> grid.updateCell(pos, CellState(true)) }
            message.set("Stare and remember!")
            ui.getTimer().schedule(level.config.previewTime, Runnable { state = Solve() })
        }

        override fun handleSelect(position: Int) {
            // no selection allowed in preview mode
        }
    }

    inner class Solve : State {

        var numCorrectMatches = 0

        constructor() {
            // clear all cells
            grid.fill(CellState())
            message.set("Remember circle locations?")
        }

        override fun handleSelect(position: Int) {
            // can't select same position twice
            if (selected.contains(position))
                return

            selected.add(position)

            val correct = solution.contains(position)
            val match
                    = if (correct)
                        CellState.Match.CORRECT
                      else
                        CellState.Match.WRONG

            val cellState = CellState(correct, match)

            grid.updateCell(position, cellState)

            if (correct)
                numCorrectMatches++

            if (numCorrectMatches == solution.size) {
                message.set("Good Job!")
                state = Done()
                listener?.gameEnded(isWin = true)
            }
        }
    }

    inner class Done : State {

        override fun handleSelect(position: Int) {
        }
    }
}