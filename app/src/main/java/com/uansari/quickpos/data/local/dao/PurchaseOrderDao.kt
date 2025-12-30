package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.PurchaseOrder
import com.uansari.quickpos.data.local.entity.PurchaseOrderItem
import com.uansari.quickpos.data.local.relation.PurchaseOrderWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseOrderDao {

    @Transaction
    @Query("SELECT * FROM purchase_orders ORDER BY orderDate DESC")
    fun getAllPurchaseOrders(): Flow<List<PurchaseOrderWithItems>>

    @Transaction
    @Query("SELECT * FROM purchase_orders WHERE id = :orderId")
    fun getPurchaseOrder(orderId: Int): Flow<PurchaseOrderWithItems?>

    @Query("SELECT * FROM purchase_orders WHERE status = :status ORDER BY orderDate DESC")
    fun getPurchaseOrdersByStatus(status: String): Flow<List<PurchaseOrder>>

    @Transaction
    @Query("SELECT * FROM purchase_orders WHERE supplierId = :supplierId ORDER BY orderDate DESC")
    fun getPurchaseOrdersBySupplier(supplierId: Int): Flow<List<PurchaseOrderWithItems>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(purchaseOrder: PurchaseOrder): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertItems(items: List<PurchaseOrderItem>)

    @Update
    suspend fun update(purchaseOrder: PurchaseOrder)

    @Update
    suspend fun updateItem(item: PurchaseOrderItem)

    @Delete
    suspend fun delete(purchaseOrder: PurchaseOrder)
}