package com.hieupt.recyclerviewadapter

import android.support.annotation.IntRange
import java.util.*
import kotlin.math.min

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

    fun updateItem(@IntRange(from = 0) position: Int, item: T, notifyNow: Boolean = true) {
        this._items.apply {
            if (position in 0 until size) {
                this[position] = item
                if (notifyNow) {
                    notifyItemChanged(position)
                }
            }
        }
    }

    fun addItem(item: T, notifyNow: Boolean = true) {
        this._items.apply {
            add(item)
            if (notifyNow) {
                notifyItemInserted(size - 1)
            }
        }
    }

    fun addItems(items: List<T>, notifyNow: Boolean = true) {
        this._items.apply {
            val currentSize = size
            addAll(items)
            if (notifyNow) {
                notifyItemRangeInserted(currentSize, items.size)
            }
        }
    }

    fun insertItem(item: T, @IntRange(from = 0) position: Int, notifyNow: Boolean = true) {
        this._items.apply {
            val index = min(position, size)
            if (index == size) {
                this.add(item)
            } else {
                this.add(index, item)
            }
            if (notifyNow) {
                notifyItemInserted(index)
            }
        }
    }

    fun insertItem(items: List<T>, @IntRange(from = 0) position: Int, notifyNow: Boolean = true) {
        this._items.apply {
            val index = min(position, size)
            if (index == size) {
                this.addAll(items)
            } else {
                this.addAll(index, items)
            }
            if (notifyNow) {
                notifyItemRangeInserted(index, items.size)
            }
        }
    }

    fun removeItem(item: T, notifyNow: Boolean = true) {
        this._items.apply {
            val position = indexOf(item)
            if (position != -1) {
                removeAt(position)
                if (notifyNow) {
                    notifyItemRemoved(position)
                }
            }
        }
    }

    fun removeItems(@IntRange(from = 0) startPosition: Int, @IntRange(from = 1) itemCount: Int, notifyNow: Boolean = true) {
        this._items.apply {
            if (startPosition in 0 until size) {
                val actualCount = if (startPosition + itemCount >= size) {
                    size - startPosition
                } else {
                    itemCount
                }
                repeat(actualCount) {
                    this.removeAt(startPosition)
                }
                if (notifyNow) {
                    notifyItemRangeRemoved(startPosition, actualCount)
                }
            }
        }
    }

    fun removeItemAt(@IntRange(from = 0) position: Int, notifyNow: Boolean = true) {
        this._items.apply {
            if (position in 0 until size) {
                removeAt(position)
                if (notifyNow) {
                    notifyItemRemoved(position)
                }
            }
        }
    }

    fun removeAll(notifyNow: Boolean = true, predicate: (T) -> Boolean) {
        this._items.apply {
            this.removeAll(predicate)
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun retainAll(notifyNow: Boolean = true, predicate: (T) -> Boolean) {
        this._items.apply {
            this.retainAll(predicate)
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun moveItemTo(@IntRange(from = 0) fromPosition: Int, @IntRange(from = 0) toPosition: Int, notifyNow: Boolean = true) {
        this._items.apply {
            if (fromPosition in 0 until size && toPosition in 0 until size && fromPosition != toPosition) {
                val item = this.removeAt(fromPosition)
                this.add(toPosition, item)
                if (notifyNow) {
                    notifyItemMoved(fromPosition, toPosition)
                }
            }
        }
    }

    fun reverse(notifyNow: Boolean = true) {
        this._items.apply {
            this.reverse()
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun <R : Comparable<R>> sortBy(ascending: Boolean = true, notifyNow: Boolean = true, selector: (T) -> R?) {
        this._items.apply {
            if (ascending) {
                this.sortBy(selector)
            } else {
                this.sortByDescending(selector)
            }
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun sortWith(comparator: Comparator<in T>, notifyNow: Boolean = true) {
        this._items.apply {
            this.sortWith(comparator)
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun shuffle(random: Random? = null, notifyNow: Boolean = true) {
        this._items.apply {
            if (random != null) {
                this.shuffle(random)
            } else {
                this.shuffle()
            }
            if (notifyNow) {
                notifyDataSetChanged()
            }
        }
    }

    fun clearData(notifyNow: Boolean = true) {
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