package com.sametype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sametype.data.local.INFINITE
import com.sametype.data.local.TABLE_EXCHANGE
import com.sametype.data.local.TABLE_INFINITE_BUYING
import com.sametype.data.local.entities.Exchange
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface InfiniteBuyingDao {
    @Query("SELECT ${INFINITE.LAST_MSG} FROM $TABLE_INFINITE_BUYING WHERE uid = 1")
    fun getLastMsg(): Single<Int>

    @Query("UPDATE $TABLE_INFINITE_BUYING SET ${INFINITE.LAST_MSG} =:lastMsg  WHERE uid = 1")
    fun updateLastMsg(lastMsg : Int): Completable

    @Query("SELECT ${INFINITE.LAST_UPDATE} FROM $TABLE_INFINITE_BUYING WHERE uid = 1")
    fun getLastUpdate(): Single<Long>

    @Query("UPDATE $TABLE_INFINITE_BUYING SET ${INFINITE.LAST_UPDATE} =:update  WHERE uid = 1")
    fun updateLastUpdate(update : Long): Completable
}