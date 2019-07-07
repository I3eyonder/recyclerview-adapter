package com.hieupt.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by HieuPT on 3/26/2019.
 */
abstract class BaseRecyclerAdapter<VH : BaseRecyclerAdapter.BaseViewHolder<T>, T>(private val onItemClicked: OnItemClicked<T>? = null) : RecyclerView.Adapter<VH>() {

    abstract fun getItem(position: Int): T

    abstract fun onCreatingViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun onBindingViewHolder(holder: VH, item: T, position: Int)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            onCreatingViewHolder(parent, viewType).apply {
                setOnItemClicked(onItemClicked)
            }.also { onPostCreateViewHolder(it) }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.updateOnItemClickAction(item, position)
        onBindingViewHolder(holder, item, position)
    }

    open fun onPostCreateViewHolder(viewHolder: VH) {
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var onItemClicked: OnItemClicked<T>? = null

        internal fun setOnItemClicked(action: OnItemClicked<T>?) {
            onItemClicked = action
        }

        internal fun updateOnItemClickAction(item: T, position: Int) {
            itemView.setOnClickListener {
                callOnItemClicked(position, item)
            }
        }

        protected fun callOnItemClicked(position: Int, item: T) {
            onItemClicked?.invoke(position, item)
        }
    }
}