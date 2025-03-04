package com.example.drivenext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenext.utils.NetworkUtils

open class BaseActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        if (!NetworkUtils.isConnectedToInternet(this)) {
            startActivity(Intent(this, NoInternetActivity::class.java))
        }
    }
}
