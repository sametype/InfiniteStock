package com.sametype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sametype.data.local.TABLE_EXCHANGE
import com.sametype.data.local.TABLE_STOCK
import com.sametype.data.local.entities.Exchange
import com.sametype.data.local.entities.Stock
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface StockDao {

    @Query("SELECT * FROM $TABLE_STOCK")
    fun getStock(): Flowable<List<Stock>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStock(stock: Stock): Completable

    @Query("DELETE FROM $TABLE_STOCK")
    fun deleteAllStock()
}