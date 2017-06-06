package com.amahfouz.astute

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.amahfouz.astute.model.MainController
import com.amahfouz.astute.view.AstuteGridView

class MainActivity : AppCompatActivity() {

    var grid: AstuteGridView? = null
    var message: TextView? = null

    var controller: MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.grid = findViewById(R.id.recallGridView) as AstuteGridView
        this.message = findViewById(R.id.messageView) as TextView

        this.controller = MainController(GameView())

        this.grid?.provider = controller
    }

    inner class GameView: MainController.Game {

        override fun messageChanged() {
            message?.text = controller?.message
        }
    }
}
