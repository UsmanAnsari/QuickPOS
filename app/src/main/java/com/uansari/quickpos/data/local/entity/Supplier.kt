package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val contactPerson: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val website: String = "",
    val notes: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

