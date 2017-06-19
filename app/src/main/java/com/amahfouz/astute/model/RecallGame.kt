package com.amahfouz.astute.model

import com.amahfouz.astute.model.api.GameUi

/**
 * State of a recall game.
 *
 * The game can be in two modes, preview and solve.
 */
class RecallGame(val config: Config) : GameUi.Grid.Listener {

    // grid UI
    private val grid : GameUi.Grid
        get() = config.ui.getGrid()

    // message UI
    private val message : GameUi.Message
        get() = config.ui.getMessage()

    // cells randomly chosen as the puzzle code
    val solution = RandomSet(config.setSize, config.dims.size - 1)

    // cells user have selected so far in SOLVE mode
    var selected : MutableList<Int> = mutableListOf()

    // cur state of the game
    var state : State = Preview()

    init {
        config.ui.getGrid().setListener(this)
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

    class Config(val ui : GameUi,
                 val dims: GameUi.Grid.Dims,
                 val setSize: Int,
                 val previewTime: Long)

    interface State {
        fun handleSelect(position: Int)
    }

    inner class Preview : State {

        constructor() {
            grid.resize(config.dims)
            grid.fill(CellState())
            solution.forEach { pos -> grid.updateCell(pos, CellState(true)) }
            message.set("Stare and remember!")
            config.ui.getTimer().schedule(config.previewTime, Runnable { state = Solve() })
        }

        override fun handleSelect(position: Int) {
            // no selection allowed in preview mode
        }
    }

    inner class Solve : State {

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

            val match
                    = if (solution.contains(position))
                        CellState.Match.CORRECT
                    else
                        CellState.Match.WRONG

            val cellState = CellState(true, match)

            grid.updateCell(position, cellState)
        }
    }
}