package uoc.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
//acceder a los id's
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    private val tag = "Pantalla1 - PRUEBAS"
    private val totalnq = "3" // número de preguntas totales del cuestionario
    private var numberQ = 1 // número de la pregunta actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener {
            checkValue()
        }

        //inicializo numberQ a primera pregunta
        SharedData.prefs.setSharedQuestion(this.numberQ)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "Dentro del evento onStart()")
        //obtenemos el número de la pregunta actual
        this.numberQ = SharedData.prefs.getSharedQuestion()
        tvNumberQ.text = this.numberQ.toString()+"/"+totalnq

        when (this.numberQ){ //mostramos datos de cada pregunta
            1 -> {
                tvQuestion.text = getString(R.string.question1)
                tvEquation.text = getString(R.string.eq1)
                rbAnswer1.text = getString(R.string.ans11)
                rbAnswer2.text = getString(R.string.ans12)
            }
            2 -> {
            tvQuestion.text = getString(R.string.question2)
            tvEquation.text = getString(R.string.eq2)
            rbAnswer1.text = getString(R.string.ans21)
            rbAnswer2.text = getString(R.string.ans22)
        }
            3 -> {
                tvQuestion.text = getString(R.string.question3)
                tvEquation.text = getString(R.string.eq3)
                rbAnswer1.text = getString(R.string.ans31)
                rbAnswer2.text = getString(R.string.ans32)
            }
            else -> {

            }
        }
    }


    private fun sendMessage(message: String) {
        //función para enviar el Toast al usuario
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun checkValue() {
        if (rdgroup.getCheckedRadioButtonId() == -1) { // no radio buttons are checked
            sendMessage("Choose an answer to continue")
        } else { // one of the radio buttons is checked
            //next screen
            val intent = Intent(this, Pantalla2Activity::class.java)
            startActivity(intent)
            Log.d(tag, "Intenta lanzar Pantalla2")
        }
    }
}
