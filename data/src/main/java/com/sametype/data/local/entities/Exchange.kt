package com.sametype.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sametype.data.local.EXCHANGE
import com.sametype.data.local.TABLE_EXCHANGE

@Entity(tableName = TABLE_EXCHANGE)
data class Exchange(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = EXCHANGE.COLUMN_DATE) val date: Long,
    @ColumnInfo(name = EXCHANGE.COLUMN_TICKER) val ticker : String,
    @ColumnInfo(name = EXCHANGE.COLUMN_TYPE) val type : Int,
    @ColumnInfo(name = EXCHANGE.COLUMN_COUNT) val count : Int,
    @ColumnInfo(name = EXCHANGE.COLUMN_PRICE) val price : Float,
    @ColumnInfo(name = EXCHANGE.COLUMN_TAX) val tax : Float
)