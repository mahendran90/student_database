package com.example.navigationview.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.navigationview.db.model.Student

@Dao
interface StudentDao : BaseDAO<Student> {

    @Query(value = "Select * from student")
    suspend fun getStudentList(): List<Student>

    @Query(value = "Select * from student where id =:ids")
    suspend fun getIndividualStudent(ids: Long): Student

}