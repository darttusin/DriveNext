package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class CongratsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.congrats)

        var nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
