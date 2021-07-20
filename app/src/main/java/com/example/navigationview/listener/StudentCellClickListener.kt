package com.example.navigationview.listener

import com.example.navigationview.db.model.Student

interface StudentCellClickListener {
    fun itemClickListener(student: Student)
}