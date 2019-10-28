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
    private val totalnq = 3 // número de preguntas totales del cuestionario
    private var numberQ = -1 // número de la pregunta actual
    private val NAME_REQUEST = 1
    private var count = 0
    //preguntas
    private val q1: List<String> = listOf("What value of y is a solution to this equation?", "10 y = 70", "y = 7","y = 23")
    private val q2: List<String> = listOf("What value of y is a solution to this equation?", "3 y = 15", "y = 2","y = 5")
    private val q3: List<String> = listOf("Pithagoras' theorem is synthesized in", "c = a + b", "True","False")
    private var questions = ArrayList<List<String>>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener {
            checkValue()
        }

        //inicializamos valores
        count = count + 1
        print("Numero veces en onCreate")
        print(count)
        numberQ = 1

        questions.add(q1)
        questions.add(q2)
        questions.add(q3)

        for (i in questions) {
            println(i)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "Dentro del evento onStart()")
        //obtenemos el número de la pregunta actual
        /*
        if (create == false) {
            val bundle: Bundle? = intent.extras
            numberQ = bundle?.getInt("currentQ")!!
        } else
            create = false

         */

        //println(numberQ)
        tvNumberQ.text = this.numberQ.toString()+"/"+ totalnq.toString()

        when (this.numberQ){ //mostramos datos de cada pregunta
            1 -> {
                tvQuestion.text = questions.get(0).get(0)
                tvEquation.text = questions.get(0).get(1)
                rbAnswer1.text = questions.get(0).get(2)
                rbAnswer2.text = questions.get(0).get(3)
            }
            2 -> {
                tvQuestion.text = questions.get(1).get(0)
                tvEquation.text = questions.get(1).get(1)
                rbAnswer1.text = questions.get(1).get(2)
                rbAnswer2.text = questions.get(1).get(3)
        }
            3 -> {
                tvQuestion.text = questions.get(2).get(0)
                tvEquation.text = questions.get(2).get(1)
                rbAnswer1.text = questions.get(2).get(2)
                rbAnswer2.text = questions.get(2).get(3)
            }
            else -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            NAME_REQUEST -> {
                val cQ = data?.extras?.get("currentQ").toString()
                numberQ = cQ.toInt()
                tvNumberQ.text = this.numberQ.toString()+"/"+ totalnq.toString()
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
            //obtenemos el texto de la respuesta elegida
            var result = checkAnswer(numberQ)
            //(pasamos respuesta elegida, num pregunta actual y num preguntas
            println(result)
            val intent = Intent(this, Pantalla2Activity::class.java)
            intent.putExtra("result", result)
            intent.putExtra("currentQ", this@MainActivity.numberQ)
            intent.putExtra("totalQ", this@MainActivity.totalnq)
            startActivityForResult(intent, NAME_REQUEST)
            /*startActivity(Intent(this@MainActivity, Pantalla2Activity::class.java).apply {
                putExtra("result", result)
                putExtra("currentQ", this@MainActivity.numberQ)
                putExtra("totalQ", this@MainActivity.totalnq)
                //putExtra("currentQuestionId", this@MainActivity.currentQuestionId)
                //putExtra("len", this@MainActivity.questions!!.size)
            })*/
            //val intent = Intent(this, Pantalla2Activity::class.java)
            //startActivity(intent)
            //Log.d(tag, "Intenta lanzar Pantalla2")
        }
    }

    private fun checkAnswer(nq: Int): Int {
        when (nq) { //pregunta actual
            1 -> {
                if (rbAnswer1.isChecked)
                    return 1
                else return 0
            }

            2, 3 -> {
                if (rbAnswer2.isChecked)
                    return 1
                else return 0
            }

            else -> {
                return -1
            }
            //0 - respuenta incorrecta
            //1 - respuesta correcta
            //-1 - otros casos
        }
    }
}
