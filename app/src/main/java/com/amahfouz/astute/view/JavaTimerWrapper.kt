package com.amahfouz.astute.view

import com.amahfouz.astute.model.api.GameUi
import java.util.*

/**
 * Wraps a Java time and exposes a platform-neutral interface
 */
class JavaTimerWrapper : GameUi.Timer {

    val timer = Timer()

    override fun schedule(delay: Long, r: Runnable): GameUi.Timer.Task {
        val task = Task(r)
        timer.schedule(task, delay)
        return task
    }

    inner class Task(val r: Runnable) : GameUi.Timer.Task, TimerTask() {

        override fun run() {
            r.run()
        }
    }
}
