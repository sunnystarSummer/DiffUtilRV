package com.example.diffutilrv

import java.util.*

enum class EmployeeSort : Comparator<Employee> {
    ByRole, ByName;

    override fun compare(data01: Employee, data02: Employee): Int {
        return when (this) {
            ByRole -> data02.name?.let { data01.name?.compareTo(it) }!!
            ByName -> data01.role.let { data02.role.compareTo(it) }
        }
    }
}