package com.uansari.quickpos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.uansari.quickpos.data.local.entity.Category
import com.uansari.quickpos.data.local.entity.Product
import com.uansari.quickpos.data.local.entity.Supplier

data class ProductWithDetails(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "categoryId", entityColumn = "id"
    ) val category: Category?,
    @Relation(
        parentColumn = "supplierId", entityColumn = "id"
    ) val supplier: Supplier?,
)
