package com.example.dovui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val playGame : TextView=findViewById(R.id.btn_playgame)
        playGame.setOnClickListener {
            val intent =Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        val outGame: TextView=findViewById(R.id.btn_outgame)
        outGame.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN);
            startActivity(intent)
            finish();



        }
    }


}