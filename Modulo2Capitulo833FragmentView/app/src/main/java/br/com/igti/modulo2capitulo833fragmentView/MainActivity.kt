package br.com.igti.modulo2capitulo833fragmentView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment =FirstFragment()
        fragmentTransaction.add(R.id.firstFragmentContainer, firstFragment)

        val secondFragment =SecondFragment()
        fragmentTransaction.add(R.id.sSecondFragmentContainer, secondFragment)

        fragmentTransaction.commit()
    }
}