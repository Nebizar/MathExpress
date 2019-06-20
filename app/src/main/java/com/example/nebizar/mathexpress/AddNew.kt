package com.example.nebizar.mathexpress

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_new.*

class AddNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        textView2.text = "Add new equation and its solution !"

        button4.setOnClickListener {
            if(validation()){
                val eq = editText.text.toString()
                val sol = editText2.text.toString()
            }
        }

        button5.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun validation(): Boolean{
        var validate = false

        if (!editText2.text.toString().equals("") && !editText.text.toString().equals("")){
            validate = true
        }else{
            validate = false
            val toast = Toast.makeText(this,"Fill all details", Toast.LENGTH_LONG).show()
        }

        return validate
    }
}
