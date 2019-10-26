package uoc.quizz

import android.content.Context
import android.content.SharedPreferences

class MyPreference(context: Context) {

    private val prefName: String = "uoc.quizz"
    private val sharedQuestion: String = "question"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefName,0) //modo privado

    fun getSharedQuestion(): Int {
        return preferences.getInt(sharedQuestion,1)
    }

    fun setSharedQuestion(count: Int){
        val editor = preferences.edit()
        editor.putInt(sharedQuestion,count)
        editor.apply()
    }

    fun incSharedQuestion(){
        //incrementamos a la siguiente pregunta
        val number = getSharedQuestion() + 1
        val editor = preferences.edit()
        editor.putInt(sharedQuestion,number)
        editor.apply()
    }

    fun deleteSharedQuestion(){
        val editor = preferences.edit()
        editor.remove(sharedQuestion)
        editor.apply()
    }

}