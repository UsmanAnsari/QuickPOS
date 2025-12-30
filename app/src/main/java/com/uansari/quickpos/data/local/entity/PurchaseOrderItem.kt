package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchase_order_items", foreignKeys = [
        ForeignKey(
        entity = PurchaseOrder::class,
        parentColumns = ["id"],
        childColumns = ["purchaseOrderId"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Product::class,
        parentColumns = ["id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.RESTRICT
    )], indices = [Index("purchaseOrderId"), Index("productId")]
)
data class PurchaseOrderItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val purchaseOrderId: Int,
    val productId: Int,
    val quantity: Int,
    val unitPrice: Double,
    val receivedQuantity: Int = 0
)
