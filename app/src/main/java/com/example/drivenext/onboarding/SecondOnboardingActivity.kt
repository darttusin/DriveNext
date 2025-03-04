package com.example.drivenext.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.drivenext.BaseActivity
import com.example.drivenext.R
import com.example.drivenext.StartedActivity

class SecondOnboardingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_2)
        setupNextButton()
    }

    private fun setupNextButton() {
        val skipButton: Button = findViewById(R.id.skip_button)
        skipButton.setOnClickListener {
            startActivity(Intent(this, StartedActivity::class.java))
        }

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            startActivity(Intent(this, ThirdOnboardingActivity::class.java))
        }
    }
}
