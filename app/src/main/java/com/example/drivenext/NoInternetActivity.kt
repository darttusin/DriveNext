package com.example.drivenext

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenext.utils.NetworkUtils

class NoInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet)

        val retryButton: Button = findViewById(R.id.retry_button)
        retryButton.setOnClickListener {
            if (NetworkUtils.isConnectedToInternet(this)) {
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, проверьте подключение", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
