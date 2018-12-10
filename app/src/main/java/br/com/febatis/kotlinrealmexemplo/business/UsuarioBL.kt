package br.com.febatis.kotlinrealmexemplo.business

import br.com.febatis.kotlinrealmexemplo.data.entity.Usuario
import br.com.febatis.kotlinrealmexemplo.data.realm.RealmUtils
import io.realm.RealmResults
import io.realm.Sort

// Geralmente usada para colocar procedimentos e validações
// pertinentes ao modelo de negocio do CRUD da entidade
// Exemplos em https://realm.io/docs/java/latest
class UsuarioBL {

    /**
     * "R" do cRud, Read, usado para buscar todos os usuarios no banco
     */
    fun listar(): RealmResults<Usuario> {
        val realm = RealmUtils.getInstance()
        return realm.where(Usuario::class.java).findAll()
    }


    /**
     * Também o "R" do cRud, Read, usado para buscar todos os usuarios no banco,
     * que tenham parte do nome passado por parametro
     */
    fun pesquisar(nome: String) {

    }

    /** "C" e "U" do CrUd, Create e Update, usado para adicionar e editar um usuario no banco,
     * essa função verifica se o usuario passado por parametro já tem um id, se não tiver,
     * significa que deve ser criado um novo usuario no banco, caso já tenha um id,
     * então deve ser editado
     */
    fun salvar(usuario: Usuario) {
        val realm = RealmUtils.getInstance()

        //Se for iqual a 0, criar um novo id para ele
        realm.beginTransaction()

        if(usuario.id == 0 ) {
            val list = listar()
            if(list.size > 0) {
                val lastUsuario = list
                        .sort("id", Sort.DESCENDING)
                        .first()

                usuario.id = lastUsuario?.id?.plus(1)!!
            }
        }

        realm.insertOrUpdate(usuario) // Persist
        realm.commitTransaction()
    }

    /**
     * "D" do cruD, Delete, usuado para excluir o usuario que tem o id passado por parametro
     */
    fun excluir(id: Int?) {
        val realm = RealmUtils.getInstance()

        listar().forEach {
            if (it.id == id) {
                realm.beginTransaction()
                it.deleteFromRealm()
                realm.commitTransaction()
            }
        }

    }

}