package com.sametype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sametype.data.local.EXCHANGE
import com.sametype.data.local.TABLE_EXCHANGE
import com.sametype.data.local.entities.Exchange
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ExchangeDao {
    @Query("SELECT * FROM $TABLE_EXCHANGE WHERE ${EXCHANGE.COLUMN_TICKER} = :ticker")
    fun getExchangeByTicker(ticker: String): Flowable<List<Exchange>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchange(exchange: List<Exchange>): Completable

    @Query("DELETE FROM $TABLE_EXCHANGE")
    fun deleteAllExchange()
}