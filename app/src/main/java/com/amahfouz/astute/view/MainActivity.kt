package com.amahfouz.astute.view

import android.support.v7.app.AppCompatActivity
import android.support.design.widget.Snackbar
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.amahfouz.astute.R
import com.amahfouz.astute.model.MainController
import com.amahfouz.astute.model.api.GameUi
import java.util.*

/**
 * Main UI that includes the game grid view.
 */
class MainActivity : AppCompatActivity() {

    var grid: AstuteGridView? = null
    var message: TextView? = null
    var controller: MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.grid = findViewById<AstuteGridView>(R.id.recallGridView) as AstuteGridView
        this.message = findViewById<TextView>(R.id.messageView) as TextView

        this.controller = MainController(GameView())
    }

    /**
     * Wraps Android-specific classes and exposes
     * platform-independent concepts.
     */
    inner class GameView: GameUi {

        val timerWrapper = JavaTimerWrapper()
        val popup = MessagePopup()
        val messageRef = MessageView()

        //
        // interface methods
        //

        override fun getPopup() : GameUi.Popup {
            return popup
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

        inner class MessageView : GameUi.Message {
            override fun set(msg: String) {
                message?.text = msg
            }

        }

        inner class MessagePopup : GameUi.Popup {

            var bar : Snackbar? = null

            override fun hide() {
                bar?.dismiss()
            }

            override fun show(message: String, actionLabel: String, action: () -> Unit) {
                val bar = Snackbar.make(grid as View, message, Snackbar.LENGTH_INDEFINITE)

                if (action != null) {
                    bar.setAction(actionLabel, { _ -> action() })
                }
                bar.show()
            }
        }
    }

    /**
     * Wraps a Java time and exposes a platform-neutral interface
     */
    inner class JavaTimerWrapper : GameUi.Timer {

        val timer = Timer()

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
