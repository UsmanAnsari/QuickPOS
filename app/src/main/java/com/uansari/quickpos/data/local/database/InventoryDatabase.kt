package com.uansari.quickpos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uansari.quickpos.data.local.dao.CategoryDao
import com.uansari.quickpos.data.local.dao.CustomerDao
import com.uansari.quickpos.data.local.dao.ProductDao
import com.uansari.quickpos.data.local.dao.PurchaseOrderDao
import com.uansari.quickpos.data.local.dao.SaleDao
import com.uansari.quickpos.data.local.dao.SupplierDao
import com.uansari.quickpos.data.local.entity.Category
import com.uansari.quickpos.data.local.entity.Customer
import com.uansari.quickpos.data.local.entity.Product
import com.uansari.quickpos.data.local.entity.PurchaseOrder
import com.uansari.quickpos.data.local.entity.PurchaseOrderItem
import com.uansari.quickpos.data.local.entity.Sale
import com.uansari.quickpos.data.local.entity.SaleItem
import com.uansari.quickpos.data.local.entity.Supplier

@Database(
    entities = [Product::class, Category::class, Supplier::class, Customer::class, Sale::class, SaleItem::class, PurchaseOrder::class, PurchaseOrderItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun supplierDao(): SupplierDao
    abstract fun customerDao(): CustomerDao
    abstract fun saleDao(): SaleDao
    abstract fun purchaseOrderDao(): PurchaseOrderDao

    companion object {
        const val DATABASE_NAME = "inventory_database"
    }
}