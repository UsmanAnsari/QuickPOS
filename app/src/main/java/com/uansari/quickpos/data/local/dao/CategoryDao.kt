package com.uansari.quickpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uansari.quickpos.data.local.entity.Category
import com.uansari.quickpos.data.local.relation.CategoryWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoriesWithProducts(): Flow<List<CategoryWithProducts>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategory(categoryId: Int): Flow<Category?>

    @Query("SELECT * FROM categories WHERE parentCategoryId IS NULL")
    fun getRootCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE parentCategoryId = :parentId")
    fun getSubcategories(parentId: Int): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}