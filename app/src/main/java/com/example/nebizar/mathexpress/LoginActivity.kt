package com.example.nebizar.mathexpress

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.io.FileOutputStream

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fileName = "BS.txt"
        val file = File(filesDir, fileName)
        if(!file.exists()){
            file.createNewFile()
        }

        //init db
        var dbHandler = DatabaseHandler(this)

        button9.setOnClickListener(){
            if(validation()) {
                var username = editText4.text.toString()
                var password = editText5.text.toString()
                var user = dbHandler!!.validateUser(username, password)
                if(!user.username.equals("")){
                    //save looged user in cache
                    val fileOutputStream: FileOutputStream
                    try {
                        fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
                        fileOutputStream.write((user.id.toString()+",").toByteArray())
                        fileOutputStream.write((user.username+",").toByteArray())
                        fileOutputStream.write((user.password+",").toByteArray())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    val thread = Thread() {
                        run {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    thread.start()
                }
                else{
                    val toast = Toast.makeText(this,"Wrong Username or Password", Toast.LENGTH_LONG).show()
                }
            }
        }

        button10.setOnClickListener(){
            var user: User = User()
            if(validation()) {
                var success: Boolean = false
                user.id = 1//dbHandler!!.getMaxID() + 1
                user.username = editText4.text.toString()
                user.password = editText5.text.toString()
                success = dbHandler!!.addUser(user)

                if (success){
                    val toast = Toast.makeText(this,"Saved Successfully", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun validation(): Boolean{
        var validate = false

        if (!editText4.text.toString().equals("") && !editText5.text.toString().equals("")){
            validate = true
        }else{
            validate = false
            val toast = Toast.makeText(this,"Fill all details", Toast.LENGTH_LONG).show()
        }

        return validate
    }
}

