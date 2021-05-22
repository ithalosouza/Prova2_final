package br.com.cotemig.ithalo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.ithalo.R
import br.com.cotemig.ithalo.models.ListaDespesa
import coil.load

class FaturaAdapter(var context: Context, var list: List<ListaDespesa>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_despesa, parent, false)
        return FaturaHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FaturaHolder).bind(context, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FaturaHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(context: Context, list: ListaDespesa) {

            var imagem = view.findViewById<ImageView>(R.id.image)
            imagem.load(list.imagem)

            var descricao = view.findViewById<TextView>(R.id.description)
            descricao.text = list.descricao

            var tipo = view.findViewById<TextView>(R.id.type)
            tipo.text = list.tipo

            var valor = view.findViewById<TextView>(R.id.value)
            valor.text = list.valor.toString()

            var data = view.findViewById<TextView>(R.id.date)
            data.text = list.data
        }
    }
}