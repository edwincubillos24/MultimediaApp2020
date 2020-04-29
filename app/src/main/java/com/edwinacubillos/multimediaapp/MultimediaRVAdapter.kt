package com.edwinacubillos.multimediaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.multimediaapp.model.Funcion
import kotlinx.android.synthetic.main.multimedia_item.view.*

class MultimediaRVAdapter(
    var funcionList: ArrayList<Funcion>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MultimediaRVAdapter.MultimediaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultimediaViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.multimedia_item, parent, false)
        return MultimediaViewHolder(itemView,  onItemClickListener)
    }

    override fun getItemCount(): Int {
        return funcionList.size
    }

    override fun onBindViewHolder(
        holder: MultimediaViewHolder,
        position: Int
    ) {
        val funcion: Funcion = funcionList[position]
        holder.bindFuncion(funcion)
    }

    class MultimediaViewHolder(
        itemView: View,
        var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var funcion: Funcion

        fun bindFuncion(funcion: Funcion) {
            this.funcion = funcion
            itemView.tv_funcion.text = funcion.nombre
            itemView.iv_icono.setImageResource(funcion.imagen)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(funcion)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(funcion: Funcion)
    }
}













