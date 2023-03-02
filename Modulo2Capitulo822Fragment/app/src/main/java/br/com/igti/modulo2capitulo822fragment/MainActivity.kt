package br.com.igti.modulo2capitulo822fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import br.com.igti.modulo2capitulo822fragment.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//o rro eh a variavel criada no build.gradle
/*
        binding.btnCadastrar.setOnClickListener {
            Toast.makeText(this, "deu certo o binding", Toast.LENGTH_SHORT).show()
            binding.txtConstraintView2.text = "Mudou usando binding"
        }

        binding.btnCadastrar.setOnClickListener {
            val intencao = Intent(this, SecondActivity::class.java)
            startActivity(intencao)
        }*/



        /**val clickBotao = findViewById<Button>(R.id.btnCadastrar)
        val imagem = findViewById<ImageView>(R.id.imageView)

        clickBotao.setOnClickListener {
            Toast.makeText(this, "Mensagem", Toast.LENGTH_SHORT).show()
            imagem.setBackgroundColor(resources.getColor(R.color.purple_700))
        }*/
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()
        ///val firstFragment =FirstFragment()
        //fragmentTransaction.add(R.id.firstFragmentContainer, firstFragment)
        //val secondFragment =SecondFragment()
        //fragmentTransaction.add(R.id.sSecondFragmentContainer, secondFragment)
        //fragmentTransaction.commit()

    }
}