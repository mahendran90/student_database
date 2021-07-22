package com.example.navigationview.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.navigationview.db.model.Student

@Dao
interface StudentDao : BaseDAO<Student> {

    @Query(value = "Select * from student")
    fun getStudentList(): LiveData<List<Student>>

    @Query(value = "Select * from student where id =:ids")
    fun getIndividualStudent(ids: Long): LiveData<Student>
}