package com.example.navigationview.repository

import com.example.navigationview.db.AppDatabase
import com.example.navigationview.db.model.Student
import java.lang.Exception
import javax.inject.Inject

class StudentRepository @Inject constructor(private val database: AppDatabase) {

    /**
     * Fetch multiple student from data base
     */
    suspend fun getStudentList(): List<Student> {
        return database.studentDao().getStudentList()
    }

    /**
     * Insert individual student record in Table
     */
    suspend fun addStudentInfo(student: Student): Long {
        return database.studentDao().insert(student)
    }

    /**
     * Get Individual student record from db with Student primary ID
     */
    suspend fun getIndividualStudent(id: Long): Student {
        return database.studentDao().getIndividualStudent(id)
    }

    /**
     * Update student record which consist of student primary ID
     */
    suspend fun updateStudentInfo(student: Student): Int {
        return try {
            database.studentDao().update(student)
            return 1
        }catch (e:Exception){
            return 0
        }
    }
}