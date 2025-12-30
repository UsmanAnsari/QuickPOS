package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.Sale
import com.uansari.quickpos.data.local.entity.SaleItem
import com.uansari.quickpos.data.local.relation.SaleWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {

    @Transaction
    @Query("SELECT * FROM sales ORDER BY date DESC LIMIT :limit")
    fun getRecentSales(limit: Int = 50): Flow<List<SaleWithItems>>

    @Transaction
    @Query("SELECT * FROM sales WHERE id = :saleId")
    fun getSaleWithItems(saleId: Int): Flow<SaleWithItems?>

    @Transaction
    @Query("SELECT * FROM sales WHERE customerId = :customerId ORDER BY date DESC")
    fun getSalesByCustomer(customerId: Int): Flow<List<SaleWithItems>>

    @Transaction
    @Query("""
        SELECT * FROM sales 
        WHERE date BETWEEN :startDate AND :endDate 
        ORDER BY date DESC
    """)
    fun getSalesByDateRange(startDate: Long, endDate: Long): Flow<List<SaleWithItems>>

    @Query("SELECT * FROM sales WHERE status = :status ORDER BY date DESC")
    fun getSalesByStatus(status: String): Flow<List<Sale>>

    @Query("SELECT SUM(total) FROM sales WHERE status = 'COMPLETED' AND date BETWEEN :startDate AND :endDate")
    fun getTotalRevenue(startDate: Long, endDate: Long): Flow<Double?>

    @Query("SELECT COUNT(*) FROM sales WHERE date BETWEEN :startDate AND :endDate")
    fun getSalesCount(startDate: Long, endDate: Long): Flow<Int>

    @Query("SELECT AVG(total) FROM sales WHERE status = 'COMPLETED' AND date BETWEEN :startDate AND :endDate")
    fun getAverageOrderValue(startDate: Long, endDate: Long): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(sale: Sale): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSaleItems(items: List<SaleItem>)

    @Update
    suspend fun update(sale: Sale)

    @Delete
    suspend fun delete(sale: Sale)

    @Query("DELETE FROM sale_items WHERE saleId = :saleId")
    suspend fun deleteSaleItems(saleId: Int)
}