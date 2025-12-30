package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun getCustomer(customerId: Int): Flow<Customer?>

    @Query("SELECT * FROM customers WHERE phone = :phone LIMIT 1")
    suspend fun getCustomerByPhone(phone: String): Customer?

    @Query("""
        SELECT * FROM customers 
        WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchCustomers(query: String): Flow<List<Customer>>

    @Query("SELECT * FROM customers ORDER BY totalPurchases DESC LIMIT :limit")
    fun getTopCustomers(limit: Int = 10): Flow<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(customer: Customer): Long

    @Update
    suspend fun update(customer: Customer)

    @Query("UPDATE customers SET totalPurchases = totalPurchases + :amount WHERE id = :customerId")
    suspend fun incrementTotalPurchases(customerId: Int, amount: Double)

    @Query("UPDATE customers SET loyaltyPoints = loyaltyPoints + :points WHERE id = :customerId")
    suspend fun addLoyaltyPoints(customerId: Int, points: Int)

    @Delete
    suspend fun delete(customer: Customer)
}