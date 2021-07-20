package com.example.navigationview.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.navigationview.db.RoomConstant

@Entity(tableName = RoomConstant.TABLE_STUDENT)
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "phone") val phone: String?
)
