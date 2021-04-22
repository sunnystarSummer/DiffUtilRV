package com.example.diffutilrv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val employeeRecyclerViewAdapter = EmployeeRecyclerViewAdapter()
    private var employeeViewModel: EmployeeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewModel
        //當呼叫setValue時，清單畫面的資料將會更新
        employeeViewModel = EmployeeViewModel().getViewModel(this)
        employeeViewModel!!.observe(this, Observer { employees ->
            //在 setList 的實作中，有 notifyDataSetChanged
            //當呼叫 setList 時，畫面也就隨之更新了
            employeeRecyclerViewAdapter.setList(employees as List<Employee>)
        })

        //畫面初始化，清單畫面
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)

        //將ViewModel預設的資料，導入暫存清單資料至清單
        employeeRecyclerViewAdapter.list = employeeViewModel?.getEmployees()
        recyclerView.adapter = employeeRecyclerViewAdapter
    }

    override fun onStart() {
        super.onStart()
        //排序，ByRole
        setEmployeeListView(EmployeeSort.ByRole)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> {
                //排序，ByName
                setEmployeeListView(EmployeeSort.ByName)
                return true
            }
            R.id.sort_by_role -> {
                //排序，ByRole
                setEmployeeListView(EmployeeSort.ByRole)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setEmployeeListView(sort: EmployeeSort) {
        //方案一
        //排序資料，透過 ViewModel更新
        employeeRecyclerViewAdapter.sort(sort,Runnable {
            val list = employeeRecyclerViewAdapter.list
            employeeViewModel!!.setValue(list)
        })

        //方案二
        //其實，不需要用到 ViewModel
        //在 sort2 的實作中，有 notifyDataSetChanged
        //當呼叫 sort2 時，畫面也就隨之更新了
        employeeRecyclerViewAdapter.sort2(sort)

    }
}