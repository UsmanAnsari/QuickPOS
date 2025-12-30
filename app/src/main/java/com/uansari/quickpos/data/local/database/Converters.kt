package com.uansari.quickpos.data.local.database

import androidx.room.TypeConverter
import com.uansari.quickpos.data.local.entity.enums.OrderStatus
import com.uansari.quickpos.data.local.entity.enums.PaymentMethod
import com.uansari.quickpos.data.local.entity.enums.SaleStatus

class Converters {

    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String {
        return value.name
    }

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod {
        return PaymentMethod.valueOf(value)
    }

    @TypeConverter
    fun fromSaleStatus(value: SaleStatus): String {
        return value.name
    }

    @TypeConverter
    fun toSaleStatus(value: String): SaleStatus {
        return SaleStatus.valueOf(value)
    }

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus): String {
        return value.name
    }

    @TypeConverter
    fun toOrderStatus(value: String): OrderStatus {
        return OrderStatus.valueOf(value)
    }
}
