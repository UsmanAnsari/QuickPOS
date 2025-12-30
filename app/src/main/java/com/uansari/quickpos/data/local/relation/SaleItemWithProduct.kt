package com.uansari.quickpos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.uansari.quickpos.data.local.entity.Product
import com.uansari.quickpos.data.local.entity.SaleItem

data class SaleItemWithProduct(
    @Embedded val saleItem: SaleItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
)
