package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.drivenext.onboarding.FirstOnboardingActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        // Задержка 2 секунды, после чего открывается основной экран
        Handler().postDelayed({
            startup()
            finish()

        }, 2000) // 2000 миллисекунд = 2 секунды
    }

    private fun startup() {
        var token = false
        if (!token) {
            startActivity(Intent(this, FirstOnboardingActivity::class.java))
        } else {
            startActivity(Intent(this, StartedActivity::class.java))
        }
    }
}

