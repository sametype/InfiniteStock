package com.sametype.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sametype.data.local.INFINITE
import com.sametype.data.local.TABLE_INFINITE_BUYING

@Entity(tableName = TABLE_INFINITE_BUYING)
data class InfiniteBuying(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = INFINITE.LAST_MSG) val lastMsgId: Int,
    @ColumnInfo(name = INFINITE.LAST_UPDATE) val lastUpdate: Long
)