package com.example.drivenext.registration

import android.os.Bundle
import android.content.Intent

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.util.Patterns
import com.example.drivenext.BaseActivity
import com.example.drivenext.R


class FirstRegistrationActivity : BaseActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var agreeCheckBox: CheckBox

    private lateinit var loginButton: Button
    private lateinit var eyeButton: ImageButton
    private lateinit var eyeButton2: ImageButton

    private lateinit var emailErrorText: TextView
    private lateinit var passwordErrorText: TextView
    private lateinit var confirmPasswordErrorText: TextView
    private lateinit var agreeCheckboxErrorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_1)

        // Инициализация
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password2)
        confirmPasswordEditText = findViewById(R.id.password)
        agreeCheckBox = findViewById(R.id.agree_checkbox)

        loginButton = findViewById(R.id.login_button)
        eyeButton = findViewById(R.id.eye_button2)
        eyeButton2 = findViewById(R.id.eye_button)

        emailErrorText = findViewById(R.id.email_error)
        passwordErrorText = findViewById(R.id.password_error)
        confirmPasswordErrorText = findViewById(R.id.confirm_password_error)
        agreeCheckboxErrorText = findViewById(R.id.agree_checkbox_error)

        val chevronButton: ImageButton = findViewById(R.id.chevron_button)

        chevronButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        loginButton.setOnClickListener {
            if (validateFields()) {
                startActivity(Intent(this, SecondRegistrationActivity::class.java))
            }
        }

        setPasswordVisibilityToggle()
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (!isValidEmail(emailEditText.text.toString())) {
            emailErrorText.visibility = TextView.VISIBLE
            emailEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            emailErrorText.visibility = TextView.GONE
            emailEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        if (passwordEditText.text.toString().length < 6) {
            passwordErrorText.visibility = TextView.VISIBLE
            passwordEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            if (passwordEditText.text.toString() != confirmPasswordEditText.text.toString()) {
                confirmPasswordErrorText.visibility = TextView.VISIBLE
                confirmPasswordEditText.setBackgroundResource(R.drawable.edit_text_error_background)
                isValid = false
            } else {
                passwordErrorText.visibility = TextView.GONE
                passwordEditText.setBackgroundResource(R.drawable.edit_text_background)
                confirmPasswordErrorText.visibility = TextView.GONE
                confirmPasswordEditText.setBackgroundResource(R.drawable.edit_text_background)
            }
        }

        if (!agreeCheckBox.isChecked) {
            agreeCheckboxErrorText.visibility = TextView.VISIBLE
            isValid = false
        } else {
            agreeCheckboxErrorText.visibility = TextView.GONE
        }

        return isValid
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setPasswordVisibilityToggle() {
        eyeButton.setOnClickListener {
            togglePasswordVisibility(passwordEditText)
        }
        eyeButton2.setOnClickListener {
            togglePasswordVisibility(confirmPasswordEditText)
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
}
