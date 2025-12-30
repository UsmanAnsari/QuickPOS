package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.SET_NULL
    ), ForeignKey(
        entity = Supplier::class,
        parentColumns = ["id"],
        childColumns = ["supplierId"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index("categoryId"), Index("supplierId"), Index(
        "sku",
        unique = true
    ), Index("barcode", unique = true)]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val sku: String, // Stock Keeping Unit
    val barcode: String? = null,
    val categoryId: Int? = null,
    val supplierId: Int? = null,
    val costPrice: Double,
    val sellingPrice: Double,
    val currentStock: Int = 0,
    val minStockLevel: Int = 10,
    val maxStockLevel: Int? = null,
    val description: String = "",
    val imageUrl: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val profitMargin: Double
        get() = ((sellingPrice - costPrice) / costPrice) * 100

    val isLowStock: Boolean
        get() = currentStock <= minStockLevel

    val stockValue: Double
        get() = currentStock * costPrice
}