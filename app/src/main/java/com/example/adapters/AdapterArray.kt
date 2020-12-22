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

class AdapterArray(var lista: MutableList<String>) : RecyclerView.Adapter<AdapterArray.StringViewHolder>() {

    class StringViewHolder(var root: View, var textView: TextView,var checkBox: CheckBox) : RecyclerView.ViewHolder(root)
    var itemsChecked = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

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
                    Log.d("prueba","prueba ${Random.nextInt(lista.size)}   - - - ${lista.size}")
                    this.notifyDataSetChanged()
                }
            }
            lista.size-1 ->{
                holder.textView.text = "Insertar"
//                Log.d("prueba","prueba ${Random.nextInt(lista.size)}   - - - ${lista.size}")
                holder.textView.setOnClickListener {
                    Log.d("prueba","prueba ${lista.size} - - - ${position+1}")
                    lista.add("PC-$position")
                    it.setOnClickListener(null)
                    this.notifyDataSetChanged()
                }
            }
            lista.size-2 ->{
                holder.textView.text = "Contar encendidos"
            }
            else->{
                holder.textView.text = lista[position]
            }
        }

        holder.checkBox.setOnCheckedChangeListener { button, checked ->
            if(checked) {
                holder.root.setBackgroundColor(holder.root.context.getColor(R.color.green))
                itemsChecked++
            }else{
                holder.root.setBackgroundColor(holder.root.context.getColor(R.color.red))
                itemsChecked--
            }
            Log.d("num","$itemsChecked")
        }
    }

}