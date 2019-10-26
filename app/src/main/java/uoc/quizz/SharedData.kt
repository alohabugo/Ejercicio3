package uoc.quizz

import android.app.Application

class SharedData : Application() {
    companion object { //objeto para compartir datos en app
        lateinit var prefs: MyPreference //instanciado más tarde en OnCreate
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MyPreference(this)
    }
}