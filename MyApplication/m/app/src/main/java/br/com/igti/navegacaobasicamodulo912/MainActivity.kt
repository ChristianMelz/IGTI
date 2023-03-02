package br.com.igti.navegacaobasicamodulo912


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.igti.navegacaobasicamodulo912.databinding.ActivityMainBinding
import java.io.Serializable


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.container)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        /**fragmentTransaction.add(R.id.fragmetContainer, FirstFragment())
        fragmentTransaction.commit()*/

        var pessoa = Classeusuario("Christian", 41)


        binding.btnCadastrar.setOnClickListener {
            /*fragmentTransaction.replace(R.id.fragmetContainer, SecondFragment())
            fragmentTransaction.commit()*/

            val pacotedeinformacao = Bundle()
            pacotedeinformacao.putSerializable("ChaveIGTI", pessoa)

            val intencao = Intent(this, SecondActivity::class.java)
            intencao.putExtra("ChaveIGTI", pacotedeinformacao)
            startActivity(intencao)
        }

    }

}
data class Classeusuario(
    val nome: String,
    val idade :Int
):Serializable

