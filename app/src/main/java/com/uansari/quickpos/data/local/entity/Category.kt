package com.uansari.quickpos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories", foreignKeys = [
        ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["parentCategoryId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index("parentCategoryId")]
)
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val parentCategoryId: Int? = null,
    val description: String = "",
    val color: Int = 0xFF6200EE.toInt()
)
