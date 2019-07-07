package com.app.demo.recyclerviewadapter

import android.graphics.Color
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
class ColorAdapter(items: List<String>?, onItemClicked: OnItemClicked<String>? = null) :
    RecyclerListAdapter<ColorAdapter.ViewHolder, String>(items, onItemClicked) {

    override fun onCreatingViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun onBindingViewHolder(holder: ViewHolder, item: String, position: Int) {
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<String>(itemView) {

        fun bind(item: String) {
            itemView.backgroundLayout.setBackgroundColor(Color.parseColor(item))
        }
    }
}