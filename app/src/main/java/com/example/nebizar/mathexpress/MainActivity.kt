package com.example.nebizar.mathexpress

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = "Hello, Anon"

        button.setOnClickListener{
            val intent = Intent(this, MainGame::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, AddNew::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {

        }
    }
}
