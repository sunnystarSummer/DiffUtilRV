package com.example.diffutilrv

import android.view.View
import android.widget.TextView
import com.example.diffutilrv.baseUI.adapter.base.BaseUIAdapter
import com.example.diffutilrv.baseUI.adapter.base.BaseUIViewHolder
import java.util.*

class EmployeeRecyclerViewAdapter :
    BaseUIAdapter<Employee, EmployeeRecyclerViewAdapter.ViewHolder>() {
    fun sort(sort: EmployeeSort?, runnable: Runnable) {
        Collections.sort(list, sort)
        runnable.run()
    }

    fun sort2(sort: EmployeeSort?) {
        Collections.sort(list, sort)
        notifyDataSetChanged()
    }

    override fun setOnBindViewHolder(holder: ViewHolder, position: Int, data: Employee) {
        holder.name.text = data.name
        holder.role.text = data.role
    }

    override fun setResViewHolderID(viewVHype: Int): Int {
        return R.layout.list_item
    }

    override fun newViewHolder(view: View, viewVHype: Int): ViewHolder {
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseUIViewHolder(itemView) {
        val role: TextView = itemView.findViewById<View>(R.id.employee_role) as TextView
        val name: TextView = itemView.findViewById<View>(R.id.employee_name) as TextView

    }
}