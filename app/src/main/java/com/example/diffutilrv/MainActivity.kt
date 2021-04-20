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
    private val localRepository = LocalRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        val list = localRepository.getEmployees()
        employeeRecyclerViewAdapter.list = list
        recyclerView.adapter = employeeRecyclerViewAdapter

        //Jason
        employeeViewModel = EmployeeViewModel().getViewModel(this)
        employeeViewModel!!.observe(this, Observer { employees ->
            employeeRecyclerViewAdapter.setList(employees as List<Employee>?)
        })
    }

    override fun onStart() {
        super.onStart()
        //一開始排序，ByRole
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
                setEmployeeListView(EmployeeSort.ByName)
                return true
            }
            R.id.sort_by_role -> {
                setEmployeeListView(EmployeeSort.ByRole)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setEmployeeListView(sort: EmployeeSort) {
        employeeRecyclerViewAdapter.sort(sort,Runnable {
            val list = employeeRecyclerViewAdapter.list
            employeeViewModel!!.setValue(list)
        })
    }
}