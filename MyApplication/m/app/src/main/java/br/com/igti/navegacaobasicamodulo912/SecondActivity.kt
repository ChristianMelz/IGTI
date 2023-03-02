package br.com.igti.navegacaobasicamodulo912

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.igti.navegacaobasicamodulo912.databinding.ActivitySecondBinding

private lateinit var binding: ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.segundaActivity)

        val bundle = intent.extras?.get("ChaveIGTI") as Bundle

        val user = bundle.get("ChaveIGTI") as Classeusuario

        binding.txtConstraintView2.text = user.nome

        /*
        val recebeTexto = intent.getStringExtra("ChaveIGTI")
        binding.txtConstraintView2.text = recebeTexto */
    }
}