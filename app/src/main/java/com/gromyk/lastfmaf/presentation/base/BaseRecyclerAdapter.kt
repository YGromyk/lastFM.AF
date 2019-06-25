package com.gromyk.lastfmaf.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<T>>() {

    val items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindView(items[position])
    }

    fun replaceItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item: T)
    }
}