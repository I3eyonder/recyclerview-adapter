package com.app.demo.recyclerviewadapter

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var colorAdapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ColorAdapter(
                    listOf(
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                    )
            ) { _, item ->
                Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
            }.also {
                colorAdapter = it
            }
        }
        spinner.apply {
            adapter = ArrayAdapter(
                    context, android.R.layout.simple_list_item_1,
                    mutableListOf(
                            "Clear data",
                            "Add more",
                            "4 More",
                            "Remove first",
                            "Remove random",
                            "Remove 2 start 4",
                            "Remove all color > 5.000k",
                            "Remove all color < 5.000k",
                            "Retain all color > 5.000k",
                            "Retain all color < 5.000k",
                            "Insert to position 2",
                            "Insert 3 to position 1",
                            "Move 4 to position 1",
                            "Reverse",
                            "Shuffle",
                            "Update position 2",
                            "New list 5 items",
                            "Sort ascending",
                            "Sort descending",
                            "Sort with Comparator"
                    )
            )
        }
        button.setOnClickListener {
            when (spinner.selectedItemPosition) {
                0 -> {
                    colorAdapter.clearData()
                }
                1 -> {
                    colorAdapter.addItem(
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            true
                    )
                    recyclerView.scrollBy(0, Int.MAX_VALUE)
                }
                2 -> {
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
                3 -> {
                    colorAdapter.removeItemAt(0)
                    recyclerView.scrollToPosition(0)
                }
                4 -> {
                    try {
                        colorAdapter.removeItem(colorAdapter.items.random())
                    } catch (e: NoSuchElementException) {
                        Toast.makeText(this, "Collection is empty", Toast.LENGTH_SHORT).show()
                    }
                }
                5 -> {
                    colorAdapter.removeItems(3, 2)
                }
                6 -> {
                    colorAdapter.removeAll {
                        abs(it) > 5000000
                    }
                }
                7 -> {
                    colorAdapter.removeAll {
                        abs(it) < 5000000
                    }
                }
                8 -> {
                    colorAdapter.retainAll {
                        abs(it) > 5000000
                    }
                }
                9 -> {
                    colorAdapter.retainAll {
                        abs(it) < 5000000
                    }
                }
                10 -> {
                    colorAdapter.insertItem(
                            Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                            2
                    )
                    recyclerView.scrollToPosition(2)
                }
                11 -> {
                    colorAdapter.insertItem(
                            listOf(
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                            ),
                            1
                    )
                    recyclerView.scrollToPosition(1)
                }
                12 -> {
                    colorAdapter.moveItemTo(4, 1)
                    recyclerView.scrollToPosition(1)
                }
                13 -> {
                    colorAdapter.reverse()
                }
                14 -> {
                    colorAdapter.shuffle()
                }
                15 -> {
                    colorAdapter.updateItem(2, Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                    recyclerView.scrollToPosition(2)
                }
                16 -> {
                    colorAdapter.updateItems(
                            listOf(
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                                    Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                            )
                    )
                }
                17 -> {
                    colorAdapter.sortBy {
                        it
                    }
                }
                18 -> {
                    colorAdapter.sortBy(ascending = false) {
                        it
                    }
                }
                19 -> {
                    colorAdapter.sortWith(Comparator { t1, t2 ->
                        t1 - t2
                    })
                }
            }
        }
    }
}
