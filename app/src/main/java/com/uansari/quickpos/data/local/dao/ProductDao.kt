package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.Product
import com.uansari.quickpos.data.local.relation.ProductWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    // ============ Queries ============

    @Transaction
    @Query("SELECT * FROM products WHERE isActive = 1 ORDER BY name ASC")
    fun getAllProductsWithDetails(): Flow<List<ProductWithDetails>>

    @Transaction
    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductWithDetails(productId: Int): Flow<ProductWithDetails?>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProduct(productId: Int): Product?

    @Query("SELECT * FROM products WHERE sku = :sku LIMIT 1")
    suspend fun getProductBySku(sku: String): Product?

    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getProductByBarcode(barcode: String): Product?

    @Query("""
        SELECT * FROM products 
        WHERE isActive = 1 
        AND (name LIKE '%' || :query || '%' OR sku LIKE '%' || :query || '%')
        ORDER BY name ASC
    """)
    fun searchProducts(query: String): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE categoryId = :categoryId AND isActive = 1")
    fun getProductsByCategory(categoryId: Int): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE currentStock <= minStockLevel AND isActive = 1")
    fun getLowStockProducts(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE supplierId = :supplierId AND isActive = 1")
    fun getProductsBySupplier(supplierId: Int): Flow<List<Product>>

    @Query("SELECT COUNT(*) FROM products WHERE isActive = 1")
    fun getProductCount(): Flow<Int>

    @Query("SELECT SUM(currentStock * costPrice) FROM products WHERE isActive = 1")
    fun getTotalStockValue(): Flow<Double?>

    // ============ Inserts ============

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(products: List<Product>): List<Long>

    // ============ Updates ============

    @Update
    suspend fun update(product: Product)

    @Query("UPDATE products SET currentStock = currentStock + :quantity WHERE id = :productId")
    suspend fun increaseStock(productId: Int, quantity: Int)

    @Query("UPDATE products SET currentStock = currentStock - :quantity WHERE id = :productId")
    suspend fun decreaseStock(productId: Int, quantity: Int)

    @Query("UPDATE products SET currentStock = :newStock WHERE id = :productId")
    suspend fun updateStock(productId: Int, newStock: Int)

    @Query("UPDATE products SET updatedAt = :timestamp WHERE id = :productId")
    suspend fun updateTimestamp(productId: Int, timestamp: Long = System.currentTimeMillis())

    // ============ Deletes ============

    @Delete
    suspend fun delete(product: Product)

    @Query("UPDATE products SET isActive = 0 WHERE id = :productId")
    suspend fun softDelete(productId: Int)
}