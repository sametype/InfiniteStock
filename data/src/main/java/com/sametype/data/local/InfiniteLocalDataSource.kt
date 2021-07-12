package com.sametype.data.local

import com.sametype.data.local.entities.Exchange

class InfiniteLocalDataSource(private val database: StockDatabase) {
    fun getExchange(ticker:String) = database.exchangeDao().getExchangeByTicker(ticker)

    fun insert(exchanges :List<Exchange>) = database.exchangeDao().insertExchange(exchanges)

    fun getStocks() = database.stockDao().getStock()

    fun getLastMsgId() = database.infiniteDao().getLastMsg()

    fun updateLastMsgId(msgId : Int) = database.infiniteDao().updateLastMsg(msgId)

    fun getLastUpdate() = database.infiniteDao().getLastUpdate()

    fun updateLastUpdate(update:Long) = database.infiniteDao().updateLastUpdate(update)
}