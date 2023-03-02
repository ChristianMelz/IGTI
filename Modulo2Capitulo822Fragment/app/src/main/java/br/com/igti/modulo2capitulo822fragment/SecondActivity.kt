package br.com.igti.modulo2capitulo822fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.igti.modulo2capitulo822fragment.databinding.ActivityMainBinding
import br.com.igti.modulo2capitulo822fragment.databinding.ActivitySecondBinding


private lateinit var binding: ActivitySecondBinding


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_second)
    }
}