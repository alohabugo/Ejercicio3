package uoc.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
//acceder a los id's
import kotlinx.android.synthetic.main.activity_pantalla2.*

class Pantalla2Activity : AppCompatActivity() {

    private val tag = "Pantalla 2 - PRUEBAS:"
    //respuesta elegida
    private var chosenAnswer: Int = rdgroup.checkedRadioButtonId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)

        Log.i(tag, "Dentro del evento onCreate()")
        btnAction.setOnClickListener {
            changeScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "Dentro del evento onStart()")
        //obtenemos el número de la pregunta actual
        val numberQ: Int = SharedData.prefs.getSharedQuestion()
        checkAnswer((numberQ))
    }

    private fun checkAnswer(nq: Int) = when (nq) {
        1 -> {
            if (this.chosenAnswer == rbAnswer1.id)
                rightAnswer()
            else failedAnswer()
        }

        2, 3 -> {
            if (this.chosenAnswer == rbAnswer2.id)
                rightAnswer()
            else failedAnswer()
        }

        else -> {
            lastAnswer()
        }
    }

    private fun rightAnswer(){
        tvMessage.text = "RIGHT!!"
        btnAction.text = "NEXT"
        //pasamos a la siguiente pregunta
        SharedData.prefs.incSharedQuestion()
    }

    private fun failedAnswer(){
        tvMessage.text = "Sorry! You failed."
        btnAction.text = "TRY AGAIN"
    }

    private fun lastAnswer(){
        tvMessage.text = "Congratulations"
        btnAction.text = "START AGAIN"
        //volvemos a empezar a la primera pregunta
        SharedData.prefs.deleteSharedQuestion()
        SharedData.prefs.setSharedQuestion(1)
    }

    private fun changeScreen(){
        //next screen
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
