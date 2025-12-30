package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = Sale::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("saleId"), Index("productId")]
)
data class SaleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val saleId: Int,
    val productId: Int,
    val productName: String, // Snapshot at time of sale
    val quantity: Int,
    val unitPrice: Double,
    val discount: Double = 0.0,
    val total: Double
)