package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    @Query("SELECT * FROM suppliers WHERE isActive = 1 ORDER BY name ASC")
    fun getAllSuppliers(): Flow<List<Supplier>>

    @Query("SELECT * FROM suppliers WHERE id = :supplierId")
    fun getSupplier(supplierId: Int): Flow<Supplier?>

    @Query("""
        SELECT * FROM suppliers 
        WHERE isActive = 1 AND (name LIKE '%' || :query || '%' OR contactPerson LIKE '%' || :query || '%')
        ORDER BY name ASC
    """)
    fun searchSuppliers(query: String): Flow<List<Supplier>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(supplier: Supplier): Long

    @Update
    suspend fun update(supplier: Supplier)

    @Delete
    suspend fun delete(supplier: Supplier)

    @Query("UPDATE suppliers SET isActive = 0 WHERE id = :supplierId")
    suspend fun softDelete(supplierId: Int)
}