package com.sametype.infigetter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sametype.data.InfiniteRepository
import com.sametype.data.local.StockDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class MainActivity : AppCompatActivity() {
    private lateinit var repository: InfiniteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = InfiniteRepository(this)
        read()
    }

    fun read() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_SMS),
                REQUEST_PHONE_CALL
            )
        } else {
            repository.refresh(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t1, t2 ->
                    Log.d("DHLTEST", "new DHLTEST $t1")
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PHONE_CALL -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    repository.refresh(this)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { t1, t2 ->
                            Log.d("DHLTEST", "new DHLTEST $t1")
                        }
                } else {

                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    companion object {
        const val REQUEST_PHONE_CALL = 1
    }

}