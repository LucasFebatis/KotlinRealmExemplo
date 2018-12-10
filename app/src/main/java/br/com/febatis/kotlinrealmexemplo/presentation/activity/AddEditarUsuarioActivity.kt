package br.com.febatis.kotlinrealmexemplo.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.febatis.kotlinrealmexemplo.R
import br.com.febatis.kotlinrealmexemplo.business.UsuarioBL
import br.com.febatis.kotlinrealmexemplo.data.entity.Usuario
import io.realm.Sort

class AddEditarUsuarioActivity : AppCompatActivity() {

    private var usuarioBL : UsuarioBL = UsuarioBL()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_editar_usuario)

        initView()
    }

    private fun initView() {

        val etNome = findViewById<EditText>(R.id.etNome)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        // Definindo ação do click no botão
        btnAdd.setOnClickListener {

            // Definindo valores do usuario a ser criado
            val usuario = Usuario()
            usuario.nome = etNome.text.toString()
            usuario.email = etEmail.text.toString()
            usuario.senha = etSenha.text.toString()

            // Criando Usuario
            usuarioBL.salvar(usuario)

            finish()
        }
    }
}
