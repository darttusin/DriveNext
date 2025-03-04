package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.drivenext.registration.FirstRegistrationActivity

class StartedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started)

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val registrationButton: Button = findViewById(R.id.registration_button)
        registrationButton.setOnClickListener {
            startActivity(Intent(this, FirstRegistrationActivity::class.java))
        }
    }
}
