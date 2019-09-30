package com.example.nebizar.mathexpress

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_game.*
import android.os.SystemClock
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import java.io.File


class MainGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        var dbHandler = DatabaseHandler(this)
        var startTime = 0.toLong()
        var endTime: Long

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("The End!")

        var counter = 0
        var safety = 0
        Log.v("EXP:","test")
        //var expressions = listOf("2+2=", "2*2=", "3+3=")
        var expressions = dbHandler!!.getEquations()
        expressions = expressions.dropLast(1)
        Log.v("EXP:",expressions)
        var data = expressions.split(",")
        //var solutions = listOf("4","4", "6")
        var initial_eq = data[0].split(';')

        button7.setOnClickListener {
            startTime = SystemClock.elapsedRealtime()
            textView4.text = initial_eq[0]
            safety = 1
        }

        button6.setOnClickListener {
            val sol = editText3.text.toString()
            if(sol.equals(initial_eq[1])){
                counter += 1
                if(counter < data.size){
                    initial_eq = data[counter].split(';')
                    textView4.text = initial_eq[0]
                }
                else{
                    endTime = SystemClock.elapsedRealtime()
                    val elapsedMilliSeconds = endTime - startTime
                    val elapsedSeconds = elapsedMilliSeconds / 1000.0
                    safety = 0
                    dialog.setMessage("Congratulations ! You needed " + elapsedSeconds.toString() + " seconds to solve all the equations")
                    dialog.show()
                    updateScore(elapsedMilliSeconds)
                }
            }
            else{
                val toast = Toast.makeText(this,"Wrong answer !!!", Toast.LENGTH_LONG).show()
            }
        }



        button8.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateScore(time: Long){
        val fileName = "BS.txt"
        val file = File(filesDir, fileName)
        var user = User()

        var input = file.bufferedReader().readLines()
        var data = input[0].split(",")
        user.id = data[0].toInt()
        user.username = data[1]
        user.password = data[2]
        user.score = data[3].toInt()

        var score = time * 10 + 1000
        if (score.toInt() > user.score) user.score = score.toInt()
        AsyncTaskManager.UpdateUserScore(this).execute(user.id.toString(),user.username, user.password, user.score.toString())

    }
}
