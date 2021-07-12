package com.sametype.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.telecom.Call
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sametype.data.local.dao.ExchangeDao
import com.sametype.data.local.dao.InfiniteBuyingDao
import com.sametype.data.local.dao.StockDao
import com.sametype.data.local.entities.Exchange
import com.sametype.data.local.entities.InfiniteBuying
import com.sametype.data.local.entities.Stock

@Database(entities = [Exchange::class, InfiniteBuying::class, Stock::class], version = 1)
abstract class StockDatabase : RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao
    abstract fun infiniteDao(): InfiniteBuyingDao
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var INSTANCE: StockDatabase? = null

        fun getInstance(context: Context): StockDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StockDatabase::class.java, DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    val values = ContentValues()
                    values.put(INFINITE.LAST_MSG, 0)
                    values.put(INFINITE.LAST_UPDATE, 0L)
                    db.insert(TABLE_INFINITE_BUYING, SQLiteDatabase.CONFLICT_FAIL, values)
                }
            }).build()

    }
}