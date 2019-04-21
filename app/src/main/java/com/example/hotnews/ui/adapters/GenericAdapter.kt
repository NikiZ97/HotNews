package com.example.hotnews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var listItems: List<T>
    //private lateinit var clickListener: (T) -> Unit

    constructor(items: List<T>) {
        this.listItems = items
        //this.clickListener = clickListener
    }

    constructor() {
        this.listItems = emptyList()
    }

    fun setItems(items: List<T>) {
        this.listItems = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false), viewType)
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    internal interface Binder<T> {
        fun bind(data: T)
    }

}