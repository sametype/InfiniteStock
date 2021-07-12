package com.sametype.data.message

import android.content.Context
import android.database.Cursor
import android.provider.Telephony
import android.util.Log
import com.sametype.data.local.entities.Exchange

class MessageDataSource {
    private lateinit var projection: Array<String>
    private lateinit var selectionClause: String
    private lateinit var selectionArgs: Array<String>

    fun getExchanges(context: Context, fromID: String?, address: String = KIWOOM_ADDRESS) =
        readExchangesFromSMS(context, fromID, address)

    private fun readExchangesFromSMS(
        context: Context,
        fromID: String?,
        address: String
    ): List<Exchange> {
        Log.d("MessageDataSource", "DHLTEST readExchangesFromSMS $fromID $address")
        val retArray = mutableListOf<Exchange>()
        val uri = Telephony.Sms.Inbox.CONTENT_URI
        projection = arrayOf(
            Telephony.Sms.Inbox._ID,
            Telephony.Sms.Inbox.ADDRESS,
            Telephony.Sms.Inbox.BODY,
            Telephony.Sms.Inbox.DATE
        )
        selectionClause = "${Telephony.Sms.Inbox.ADDRESS} = ?"
        selectionArgs = arrayOf(address)
        if (!fromID.isNullOrEmpty()) {
            selectionClause += "AND ${Telephony.Sms.Inbox._ID} > ?"
            selectionArgs += fromID
        }
        val sortOder = "${Telephony.Sms.Inbox._ID} ASC"
        val cursor =
            context.contentResolver.query(uri, projection, selectionClause, selectionArgs, sortOder)
        if (cursor != null) {
            cursor.moveToFirst()
            do {
                parseToExchange(cursor)?.run { retArray.add(this) }
            } while (cursor.moveToNext())
        }
        return retArray
    }

    private fun parseToExchange(cursor: Cursor): Exchange? {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.Inbox._ID))
        val date = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.Inbox.DATE))
        val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.Inbox.ADDRESS))
        val body =
            cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.Inbox.BODY)).split("\n")
        if (body.size > 5 && body[1].contains(KIWOOM_EXCHANGE)) {
            val ticker = body[2].substringAfter("(").substringBefore(")")
            val type = when {
                body[3].contains(KIWOOM_BUY) -> 1
                body[3].contains(KIWOOM_SELL) -> 2
                else -> 0
            }
            val count = body[3].filter { it.isDigit() || it == '.' }
            val price = body[4].filter { it.isDigit() || it == '.' }
            val tax = body[5].filter { it.isDigit() || it == '.' }
            return Exchange(id, date, ticker, type, count.toInt(), price.toFloat(), tax.toFloat())
        }
        return null
    }

    companion object {
        const val KIWOOM_ADDRESS = "15449400"
        const val KIWOOM_EXCHANGE = "키움체결"
        const val KIWOOM_BUY = "매수"
        const val KIWOOM_SELL = "매도"
    }
}