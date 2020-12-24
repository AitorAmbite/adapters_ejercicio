package com.example.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.reflect.typeOf

class AdapterArray(var lista: MutableList<Int>) : RecyclerView.Adapter<AdapterArray.StringViewHolder>() {
    // Variables
    var itemsChecked = 0
    // Clases
    class StringViewHolder(var root: View, var textView: TextView,var checkBox: CheckBox) : RecyclerView.ViewHolder(root)

    //Metodos que hay que implementar porque vienen heradados del recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        //cambiarlo a view binding carajo como se haceeee
        val twTextView = view.findViewById<TextView>(R.id.tw_item)
        val checkBoxView = view.findViewById<CheckBox>(R.id.cb_item)

        return StringViewHolder(view,twTextView,checkBoxView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        when(position){
            0 -> {
                holder.textView.text = "Borrar"

                holder.textView.setOnClickListener{
                    lista.removeAt(Random.nextInt(lista.size))
                }
            }
            itemCount - 1 -> {
                holder.textView.text = "Insertar"
                holder.textView.setOnClickListener {
                    lista.add(lista.last()+1)
                    it.setOnClickListener(null)
                    this.notifyDataSetChanged()
                }
            }
            itemCount-2 ->{
                holder.textView.text = "Contar encendidos"
                holder.textView.setOnClickListener {
                    Toast.makeText(holder.root.context,"Hay ${itemsChecked} encendidos",Toast.LENGTH_LONG).show()
                }
            }
            else->{
                holder.textView.setOnClickListener(null) // esto esta asi porque cuando añadia se me quedaba el listener en el nuevo
                // es la unica manera que he encontrado de solucionarlo :(
                holder.textView.text = "pc-${lista[position]}"
            }
        }
        //aqui le aplico un listener a los checkbox para cambiar el background y sumar o restar cuantos hay on/off
        holder.checkBox.setOnCheckedChangeListener { button, checked ->
            if(checked) {
                holder.root.setBackgroundColor(holder.root.context.getColor(R.color.green))
                itemsChecked++
            }else{
                holder.root.setBackgroundColor(holder.root.context.getColor(R.color.red))
                itemsChecked--
            }
        }



    }

}