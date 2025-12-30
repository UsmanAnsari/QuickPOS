package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.uansari.quickpos.data.local.entity.enums.OrderStatus

@Entity(
    tableName = "purchase_orders",
    foreignKeys = [
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["supplierId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("supplierId"), Index("orderDate")]
)
data class PurchaseOrder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val supplierId: Int,
    val orderDate: Long = System.currentTimeMillis(),
    val expectedDate: Long? = null,
    val receivedDate: Long? = null,
    val status: OrderStatus,
    val total: Double,
    val notes: String = ""
)

