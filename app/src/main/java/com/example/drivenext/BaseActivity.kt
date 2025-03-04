package com.example.drivenext

import android.content.Intent
import android.util.Patterns
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenext.utils.NetworkUtils

open class BaseActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        if (!NetworkUtils.isConnectedToInternet(this)) {
            startActivity(Intent(this, NoInternetActivity::class.java))
        }
    }
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun togglePasswordVisibility(editText: EditText) {
        if (editText.inputType == 129) { // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
            editText.inputType = 144 // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            editText.inputType = 129 // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
        }
        editText.setSelection(editText.text.length)
    }
}
