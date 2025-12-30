package com.uansari.quickpos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.uansari.quickpos.data.local.entity.Category
import com.uansari.quickpos.data.local.entity.Product

data class CategoryWithProducts(
    @Embedded val category: Category,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val products: List<Product>
)
