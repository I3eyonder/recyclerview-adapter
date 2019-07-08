package com.app.demo.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hieupt.recyclerviewadapter.BaseRecyclerAdapter
import com.hieupt.recyclerviewadapter.OnItemClicked
import com.hieupt.recyclerviewadapter.RecyclerListAdapter
import kotlinx.android.synthetic.main.item_color.view.*

/**
 * Created by HieuPT on 7/7/2019.
 */
class ColorAdapter(items: List<Int>?, onItemClicked: OnItemClicked<Int>? = null) :
        RecyclerListAdapter<ColorAdapter.ViewHolder, Int>(items, onItemClicked) {

    override fun onCreatingViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun onBindingViewHolder(holder: ViewHolder, item: Int, position: Int) {
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<Int>(itemView) {

        fun bind(item: Int) {
            itemView.backgroundLayout.setBackgroundColor(item)
        }
    }
}