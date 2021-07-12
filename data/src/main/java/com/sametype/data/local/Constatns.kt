package com.sametype.data.local

import androidx.room.ColumnInfo

const val DATABASE_NAME = "STOCK_DB.db"
const val TABLE_EXCHANGE = "TABLE_EXCHANGE"
const val TABLE_INFINITE_BUYING = "TABLE_INFINITE_BUYING"
const val TABLE_STOCK = "TABLE_STOCK"

object EXCHANGE {
    const val COLUMN_DATE = "date"
    const val COLUMN_TICKER = "ticker"
    const val COLUMN_TYPE = "type"
    const val COLUMN_COUNT = "count"
    const val COLUMN_PRICE = "price"
    const val COLUMN_TAX = "tax"
}

object INFINITE {
    const val LAST_MSG = "last_msg_id"
    const val LAST_UPDATE = "last_update"
}

object STOCK {
    const val COLUMN_TICKER = "date"
    const val COLUMN_AVG_PRICE = "avg_price"
    const val COLUMN_HIGH_PRICE = "high_price"
    const val COLUMN_LOW_PRICE = "low_price"
    const val COLUMN_PREV_PRICE = "yesterday_price"
    const val COLUMN_CUR_COUNT = "cur_count"
    const val COLUMN_TOT_COUNT = "total_count"
    const val COLUMN_BUY_COUNT = "buy_count"
    const val COLUMN_CUR_VALUE = "cur_value"
    const val COLUMN_ONE_VALUE = "one_value"
    const val COLUMN_TARGET_VALUE = "target_value"
    const val COLUMN_RSI = "rsi"
}