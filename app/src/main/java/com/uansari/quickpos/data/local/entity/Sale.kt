package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.uansari.quickpos.data.local.entity.enums.PaymentMethod
import com.uansari.quickpos.data.local.entity.enums.SaleStatus

@Entity(
    tableName = "sales",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("customerId"), Index("date")]
)
data class Sale(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerId: Int? = null,
    val date: Long = System.currentTimeMillis(),
    val subtotal: Double,
    val tax: Double = 0.0,
    val discount: Double = 0.0,
    val total: Double,
    val paymentMethod: PaymentMethod,
    val status: SaleStatus,
    val notes: String = ""
)
