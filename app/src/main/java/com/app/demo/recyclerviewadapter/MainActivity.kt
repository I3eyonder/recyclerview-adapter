package com.app.demo.recyclerviewadapter

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var colorAdapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ColorAdapter(
                    listOf(Color.parseColor("#ff0f0f"),
                            Color.parseColor("#0fff0f"),
                            Color.parseColor("#f3e11f"),
                            Color.parseColor("#ab3ec1"))
            ) { _, item ->
                Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
            }.also {
                colorAdapter = it
            }
        }
        button.setOnClickListener {
            colorAdapter.addItem(
                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                    true
            )
            recyclerView.scrollBy(0, Int.MAX_VALUE)
        }
        button2.setOnClickListener {
            colorAdapter.addItems(
                    listOf(
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                    ),
                    true
            )
            recyclerView.scrollBy(0, Int.MAX_VALUE)
        }
    }
}
