package com.example.diffutilrv.baseUI.adapter.base

abstract class BaseUIAdapter<D, VH : BaseUIViewHolder?> : BaseAdapter<VH>() {
    internal var list: List<D>? = null
    protected abstract fun setOnBindViewHolder(holder: VH, position: Int, data: D)

    fun setList(list: List<D>?) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getList(): List<D>? {
        return list
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (getList() != null && getList()!!.size > 0) {
            val data = getList()!![position]
            setOnBindViewHolder(holder, position, data)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}