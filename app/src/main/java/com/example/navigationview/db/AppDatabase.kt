package com.example.navigationview.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationview.db.dao.StudentDao
import com.example.navigationview.db.model.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        const val DATABASE_NAME = "student_database"
    }
}