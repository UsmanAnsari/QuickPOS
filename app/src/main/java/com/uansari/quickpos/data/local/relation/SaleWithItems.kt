package com.uansari.quickpos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.uansari.quickpos.data.local.entity.Customer
import com.uansari.quickpos.data.local.entity.Sale
import com.uansari.quickpos.data.local.entity.SaleItem

data class SaleWithItems(
    @Embedded val sale: Sale,
    @Relation(
        parentColumn = "id",
        entityColumn = "saleId")
    val items: List<SaleItem>,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id")
    val customer: Customer?

)
