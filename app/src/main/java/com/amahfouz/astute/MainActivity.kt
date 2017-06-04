package com.amahfouz.astute

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amahfouz.astute.model.MainController
import com.amahfouz.astute.model.RecallGameUi
import com.amahfouz.astute.view.AstuteGridView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grid = findViewById(R.id.recallGridView) as AstuteGridView

        val controller = MainController(GameView())
        grid.model = controller.gridModel
    }

    class GameView: RecallGameUi {

        override fun reset() {
            TODO("not implemented")
        }
    }
}
