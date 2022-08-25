package com.example.dovui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    private val btnPlayagain: TextView by lazy { findViewById<TextView>(R.id.btn_playagain) }
    private val btnOutgamee: TextView by lazy { findViewById<TextView>(R.id.btn_outgamee) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)
        btnPlayagain.setOnClickListener{
            val intent =Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
        btnOutgamee.setOnClickListener{
            val intent=Intent(Intent.ACTION_MAIN);
            startActivity(intent)
            finish();
        }
    }
}