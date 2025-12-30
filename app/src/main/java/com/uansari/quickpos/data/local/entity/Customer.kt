package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String? = null,
    val phone: String,
    val address: String = "",
    val totalPurchases: Double = 0.0,
    val loyaltyPoints: Int = 0,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
