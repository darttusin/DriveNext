package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageButton
import android.util.Patterns

class LoginActivity : BaseActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailErrorText: TextView
    private lateinit var passwordErrorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        emailErrorText = findViewById(R.id.email_error)
        passwordErrorText = findViewById(R.id.password_error)

        val loginButton: Button = findViewById(R.id.login_button)
        val eyeButton: ImageButton = findViewById(R.id.eye_button)

        loginButton.setOnClickListener {
            if (validateData()) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        eyeButton.setOnClickListener {
            togglePasswordVisibility(passwordEditText)
        }
    }

    private fun togglePasswordVisibility(editText: EditText) {
        if (editText.inputType == 129) { // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
            editText.inputType = 144 // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            editText.inputType = 129 // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
        }
        editText.setSelection(editText.text.length)
    }

    private fun validateData(
    ): Boolean {
        var isValid = true

        // Сброс ошибок
        emailErrorText.visibility = TextView.GONE
        passwordErrorText.visibility = TextView.GONE

        if (!isValidEmail(emailEditText.text.toString())) {
            emailErrorText.visibility = TextView.VISIBLE
            emailEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            emailErrorText.visibility = TextView.GONE
            emailEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        if (!isValidPassword(passwordEditText.text.toString())) {
            passwordErrorText.visibility = TextView.VISIBLE
            passwordEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            passwordErrorText.visibility = TextView.GONE
            passwordEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        return isValid
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
