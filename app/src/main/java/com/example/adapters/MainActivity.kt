package com.example.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapters.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : AdapterArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerView()
    }

    private fun createRecyclerView(){
        val listaNumsPcs = mutableListOf<Int>()
        for(i in 1..Random.nextInt(6,10)){
            listaNumsPcs.add(i)
        }
        adapter = AdapterArray(listaNumsPcs.toMutableList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}