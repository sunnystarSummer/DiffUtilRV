package com.example.diffutilrv.baseUI.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : BaseUIViewHolder?> : RecyclerView.Adapter<VH>() {

    var selectedPosition = -1
        set(position) {
            field = position
            notifyDataSetChanged()
        }

    protected abstract fun setResViewHolderID(viewVHype: Int): Int
    protected abstract fun newViewHolder(view: View, viewVHype: Int): VH

    private var context: Context? = null
    private var parent: ViewGroup? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewVHype: Int): VH {
        context = parent.context
        this.parent = parent
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(setResViewHolderID(viewVHype), parent, false)
        return newViewHolder(view, viewVHype)
    }

    protected fun getString(resStringID: Int): String {
        return context!!.getString(resStringID)
    }
}