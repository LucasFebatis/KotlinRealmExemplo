package br.com.febatis.kotlinrealmexemplo.presentation.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.febatis.kotlinrealmexemplo.R
import br.com.febatis.kotlinrealmexemplo.business.UsuarioBL
import br.com.febatis.kotlinrealmexemplo.data.realm.RealmUtils
import br.com.febatis.kotlinrealmexemplo.presentation.adapter.MyAdapter

class MainActivity : AppCompatActivity() {

    var usuarioBL : UsuarioBL = UsuarioBL()

    private lateinit var rvItens: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RealmUtils.initRealm(applicationContext)

        initView()

    }

    override fun onResume() {
        super.onResume()
        obterItens()
    }

    private fun initView() {

        val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

        // Definindo a ação do Click
        fabAdd.setOnClickListener {
            val intent = Intent(applicationContext, AddEditarUsuarioActivity::class.java)
            startActivity(intent)
        }

        // Configurando o RecyclerView
        // Exemplo disponivel em: https://developer.android.com/guide/topics/ui/layout/recyclerview
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter()

        rvItens = findViewById<RecyclerView>(R.id.rvItens).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun obterItens() {
        val usuarios = usuarioBL.listar()
        viewAdapter.setItens(usuarios)
    }

}
