package com.example.diffutilrv

class Employee(private var id: Int, var name: String?, var role: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val employee = other as Employee
        if (id != employee.id) return false
        if (!role.equals(employee.role, ignoreCase = true)) return false
        return if (name != null) name == employee.name else employee.name == null
    }

    override fun hashCode(): Int {
        var result = id
        result += if (name != null) name.hashCode() else 0
        result += role.hashCode()
        return result
    }
}