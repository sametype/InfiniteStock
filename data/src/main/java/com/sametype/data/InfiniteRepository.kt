package com.sametype.data

import android.content.Context
import android.util.Log
import com.sametype.data.local.InfiniteLocalDataSource
import com.sametype.data.local.StockDatabase
import com.sametype.data.message.MessageDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class InfiniteRepository(context: Context) {
    private var msgDataSource = MessageDataSource()
    private val infiniteLocalDataSource by lazy {
        InfiniteLocalDataSource(
            StockDatabase.getInstance(
                context
            )
        )
    }

    fun refresh(context: Context) =
        infiniteLocalDataSource.getLastMsgId()
            .map {
                Log.d("DHLTEST", "DHLTEST refresh last msg id $it")
                msgDataSource.getExchanges(context, it.toString())
            }
            .doOnSuccess {
                Log.d("DHLTEST", "DHLTEST update last msg id ${it.last().uid}")
                infiniteLocalDataSource.insert(it)
                infiniteLocalDataSource.updateLastMsgId(it.last().uid)
                infiniteLocalDataSource.updateLastUpdate(System.currentTimeMillis())
            }
            .subscribeOn(Schedulers.io())
}