package br.com.febatis.kotlinrealmexemplo.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import br.com.febatis.kotlinrealmexemplo.R
import br.com.febatis.kotlinrealmexemplo.business.UsuarioBL
import br.com.febatis.kotlinrealmexemplo.data.entity.Usuario

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myDataset: MutableList<Usuario> = mutableListOf()

    private var usuarioBL : UsuarioBL = UsuarioBL()

    fun setItens(usuarios: MutableList<Usuario>) {
        //Definindo itens
        myDataset = usuarios

        //Notificando View da alteração
        notifyDataSetChanged()
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_usuario, parent, false)
        // set the view's size, margins, paddings and layout parameters
        //...
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.findViewById<TextView>(R.id.tvNome).text = myDataset[position].nome
        holder.view.findViewById<TextView>(R.id.tvEmail).text = myDataset[position].email

        holder.view.findViewById<ImageButton>(R.id.btnExcluir).setOnClickListener {
            usuarioBL.excluir(myDataset[position].id)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}