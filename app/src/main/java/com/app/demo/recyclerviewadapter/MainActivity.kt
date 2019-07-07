package com.app.demo.recyclerviewadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ColorAdapter(
                listOf("#ff0f0f", "#0fff0f", "#f3e11f", "#ab3ec1")
            ) { _, item ->
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
