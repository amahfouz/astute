package com.amahfouz.astute.view

import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.Snackbar
import android.os.Bundle
import android.view.View
import com.amahfouz.astute.R
import com.amahfouz.astute.model.MainController
import com.amahfouz.astute.model.api.GameUi
import java.util.*

import android.view.Menu
import android.view.MenuItem

/**
 * Main UI that includes the game grid view.
 */
class MainActivity : AppCompatActivity() {

    var grid: AstuteGridView? = null

    private lateinit var controller: MainController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.grid = findViewById<AstuteGridView>(R.id.recallGridView) as AstuteGridView

        this.controller = MainController(GameView(this))

        showHelp(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        return when (item.itemId) {
            R.id.action_new -> {
                startNewGame()
                true
            }

            R.id.action_help -> {
                showHelp(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showHelp(showStartButton : Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.help_text)
                .setTitle(R.string.help_dialog_title)

        if (showStartButton)
            builder.setPositiveButton("Start", { _, _ -> startNewGame() })

        val dialog = builder.create()
        dialog.show()
    }

    private fun startNewGame() {
        controller?.newGame()
        grid?.requestLayout()
    }

    //
    // Nested classes
    //

    /**
     * Wraps Android-specific classes and exposes
     * platform-independent concepts.
     */
    inner class GameView(ctx: Context): GameUi {

        private val timerWrapper = JavaTimerWrapper()
        private val toast = MessagePopup()
        private val messageRef = MessageView(ctx)

        //
        // interface methods
        //

        override fun getToast() : GameUi.Toast {
            return toast
        }

        override fun getMessage() : GameUi.Message {
            return messageRef
        }

        override fun getGrid(): GameUi.Grid {
            return grid!!
        }

        override fun getTimer(): GameUi.Timer {
            return timerWrapper
        }

        //
        // inner and nested
        //

        inner class MessageView(private val ctx: Context) : GameUi.Message {
            override fun show(msg: String) {
                val builder = AlertDialog.Builder(ctx)
                builder.setMessage(msg)
                       .setTitle(R.string.game_over_dialog_title)

                val dialog = builder.create()
                dialog.show()
            }
        }

        inner class MessagePopup : GameUi.Toast {

            private var bar : Snackbar? = null

            override fun hide() {
                bar?.dismiss()
            }

            override fun show(message: String, actionLabel: String, action: () -> Unit) {
                val bar = Snackbar.make(grid as View, message, Snackbar.LENGTH_INDEFINITE)

                if (action != null) {
                    bar.setAction(actionLabel, { action() })
                }
                bar.show()
            }
        }
    }

    /**
     * Wraps a Java time and exposes a platform-neutral interface
     */
    inner class JavaTimerWrapper : GameUi.Timer {

        private val timer = Timer()

        override fun schedule(delay: Long, r: Runnable): GameUi.Timer.Task {
            val task = Task(r)
            timer.schedule(task, delay)
            return task
        }

        inner class Task(val r: Runnable) : GameUi.Timer.Task, TimerTask() {

            override fun run() {
                runOnUiThread(r)
            }
        }
    }

}
