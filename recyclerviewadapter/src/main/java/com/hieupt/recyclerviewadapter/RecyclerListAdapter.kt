package com.hieupt.recyclerviewadapter

import java.util.*

/**
 * Created by HieuPT on 3/26/2019.
 */
abstract class RecyclerListAdapter<VH : BaseRecyclerAdapter.BaseViewHolder<T>, T>(
    items: List<T>? = null,
    onItemClicked: OnItemClicked<T>? = null
) : BaseRecyclerAdapter<VH, T>(onItemClicked) {

    private val _items: MutableList<T> = items?.let { ArrayList(it) } ?: mutableListOf()

    val items
        get() = _items.toList()

    fun updateItems(items: List<T>) {
        this._items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    fun addItem(item: T, notifyNow: Boolean = false) {
        this._items.apply {
            add(item)
            if (notifyNow) {
                notifyItemInserted(size - 1)
            }
        }
    }

    fun addItems(items: List<T>, notifyNow: Boolean = false) {
        this._items.apply {
            val currentSize = size
            addAll(items)
            if (notifyNow) {
                notifyItemRangeInserted(currentSize, items.size)
            }
        }
    }

    fun clearData(notifyNow: Boolean = false) {
        this._items.apply {
            clear()
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    override fun getItem(position: Int): T = _items[position]

    override fun getItemCount(): Int = _items.size
}