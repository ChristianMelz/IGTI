package br.com.igti.blog_aula212


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.igti.blog_aula212.adapters.RecyclerAdapter
import br.com.igti.blog_aula212.models.Blog
import br.com.igti.blog_aula212.viewModels.MainViewModel
import br.com.igti.blog_aula212.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainRecycler: RecyclerView
    private lateinit var but: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        but = findViewById(R.id.btnInserir)
        but.setOnClickListener {
            addData()
        }
        initialaiseAdapter()
    }

    private fun addData() {
        var txtPlce = findViewById<EditText>(R.id.titleTxt)
        var title = txtPlce.text.toString()
        if (title.isNotBlank()){
            Toast.makeText(this,"Entre com o Valor", Toast.LENGTH_LONG).show()
        }else{
            var blog = Blog(title)
            viewModel.adicionar(blog)
            txtPlce.text.clear()
            mainRecycler.adapter?.notifyDataSetChanged()
        }
    }

    private fun  initialaiseAdapter(){
        mainRecycler.layoutManager = viewManager
        observeData()
    }
    fun observeData(){
        viewModel.lst.observe(this, Observer {
            Log.i("data", it.toString())
            mainRecycler.adapter = RecyclerAdapter(viewModel, it, this)
        })
    }


}