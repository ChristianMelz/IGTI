package br.com.igti.aulaigtimodulo2capitulo8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Aula", "Passou pelo OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Aula", "Passou pelo OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Aula", "Passou pelo OnReStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Aula", "Passou pelo OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Aula", "Passou pelo OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Aula", "Passou pelo OnStop")
        Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Aula", "Passou pelo OnDestroy")
    }
}