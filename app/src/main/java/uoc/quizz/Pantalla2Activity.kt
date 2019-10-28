package uoc.quizz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//acceder a los id's
import kotlinx.android.synthetic.main.activity_pantalla2.*


class Pantalla2Activity : AppCompatActivity() {

    private val tag = "Pantalla 2 - PRUEBAS:"
    //respuesta elegida
    private var chosenAnswer: Int = -1
    private var totalQ: Int = -1
    private var numberQ: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)

        Log.i(tag, "Dentro del evento onCreate()")
        btnAction.setOnClickListener {
            changeScreen()
        }

        //recoger datos
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "Dentro del evento onStart()")
        //recogemos los datos recibidos
        val bundle: Bundle? = intent.extras
        //respuesta elegida
        chosenAnswer = bundle?.getInt("result")!!
        //numero de pregunta actual
        numberQ = bundle?.getInt("currentQ")
        //total de preguntas
        totalQ = bundle?.getInt("totalQ")
        //val numberQ: Int = SharedData.prefs.getSharedQuestion()
        checkAnswer((numberQ))
    }

    private fun checkAnswer(nq: Int) {
        if (nq == totalQ) {
            lastAnswer()
        } else {
            if (chosenAnswer == 1)
                rightAnswer()
            else failedAnswer()
        }
    }

    private fun rightAnswer(){
        tvMessage.text = "RIGHT!!"
        btnAction.text = "NEXT"
        //pasamos a la siguiente pregunta
        //incrementamos la pregunta
        numberQ = numberQ + 1
        println(numberQ)
    }

    private fun failedAnswer(){
        tvMessage.text = "Sorry! You failed."
        btnAction.text = "TRY AGAIN"
        //permanecemos en la pregunta
    }

    private fun lastAnswer(){
        tvMessage.text = "Congratulations"
        btnAction.text = "START AGAIN"
        //volvemos a empezar a la primera pregunta
        //asignamos la primera pregunta
        numberQ = 1
    }

    private fun changeScreen(){
        //devolver la pregunta actual
        print(numberQ)
        val result = Intent()
        result.putExtra("currentQ", this@Pantalla2Activity.numberQ)
        setResult(Activity.RESULT_OK, result)
        finish()


        //val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra("currentQ", this@Pantalla2Activity.numberQ)
        //startActivity(intent)

        //next screen
       /*val intentWithResult = Intent()
        intentWithResult.putExtra(
            "currentQ",
            numberQ
        )
        setResult(1, intentWithResult)
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        startActivity(Intent(this@Pantalla2Activity, MainActivity::class.java).apply {
            putExtra("currentQ", this@Pantalla2Activity.numberQ)
        })*/
    }
}
