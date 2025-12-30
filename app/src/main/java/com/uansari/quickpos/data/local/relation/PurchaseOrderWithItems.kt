package com.uansari.quickpos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.uansari.quickpos.data.local.entity.PurchaseOrder
import com.uansari.quickpos.data.local.entity.PurchaseOrderItem
import com.uansari.quickpos.data.local.entity.Supplier

data class PurchaseOrderWithItems(
    @Embedded val purchaseOrder: PurchaseOrder,

    @Relation(
        parentColumn = "id", entityColumn = "purchaseOrderId"
    ) val items: List<PurchaseOrderItem>,

    @Relation(
        parentColumn = "supplierId", entityColumn = "id"
    ) val supplier: Supplier
)

