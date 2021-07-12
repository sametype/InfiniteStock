package com.sametype.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sametype.data.local.STOCK
import com.sametype.data.local.TABLE_STOCK

@Entity(tableName = TABLE_STOCK)
data class Stock(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = STOCK.COLUMN_TICKER) val ticker: String?,
    @ColumnInfo(name = STOCK.COLUMN_AVG_PRICE) val avgPrice: Float,
    @ColumnInfo(name = STOCK.COLUMN_HIGH_PRICE) val highPrice: Float,
    @ColumnInfo(name = STOCK.COLUMN_LOW_PRICE) val lowPrice: Float,
    @ColumnInfo(name = STOCK.COLUMN_PREV_PRICE) val prevPrice: Float,
    @ColumnInfo(name = STOCK.COLUMN_CUR_COUNT) val curCount: Int,
    @ColumnInfo(name = STOCK.COLUMN_TOT_COUNT) val totalCount: Int,
    @ColumnInfo(name = STOCK.COLUMN_BUY_COUNT) val buyCount: Int,
    @ColumnInfo(name = STOCK.COLUMN_CUR_VALUE) val curValue: Float,
    @ColumnInfo(name = STOCK.COLUMN_ONE_VALUE) val oneValue: Float,
    @ColumnInfo(name = STOCK.COLUMN_TARGET_VALUE) val totalValue: Float,
    @ColumnInfo(name = STOCK.COLUMN_RSI) val rsi: Float,
)
