package com.example.nebizar.mathexpress
import android.os.AsyncTask
import android.view.View
//import kotlinx.android.synthetic.main.activity_message.*
import java.net.HttpURLConnection
import java.net.URL

class AsyncTaskManager{
    class UpdateUserScore( private var activity: MainGame): AsyncTask<String, String, String>(){


        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            var result: String
            var dbHandler = DatabaseHandler(activity)
            var user: User = User()
            user.id = 1
            user.username = params[0].toString()
            user.password = params[1].toString()
            user.score = params[2]!!.toInt()
            try{
                dbHandler.updateUser(user)
                return "ok"
            }finally {
                return "error"
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }
    }

}