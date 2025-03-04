package com.example.drivenext.registration

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.drivenext.BaseActivity
import com.example.drivenext.CongratsActivity
import com.example.drivenext.R
import java.text.SimpleDateFormat
import java.util.*

class ThirdRegistrationActivity : BaseActivity() {

    private lateinit var photoButton: ImageButton
    private lateinit var vyLoadButton: ImageButton
    private lateinit var vyLoad2Button: ImageButton
    private lateinit var passportLoadText: TextView
    private lateinit var vyLoadPictureText: TextView

    private lateinit var dobEditText: EditText
    private lateinit var vyNumberEditText: EditText
    private lateinit var dobErrorText: TextView
    private lateinit var vyNumberErrorText: TextView
    private lateinit var pasportLoadErrorText: TextView
    private lateinit var vyLoadErrorText: TextView

    private var vyLoaded: Boolean = false
    private var pasportLoaded: Boolean = false

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            photoButton.setImageURI(it)
        }
    }

    private val getImageVyLoad = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            passportLoadText.text = "Успешно загружено!"
            passportLoadText.setTextColor(resources.getColor(R.color.green))
            pasportLoaded = true
        }
    }

    private val getImageVyLoad2 = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            vyLoadPictureText.text = "Успешно загружено!"
            vyLoadPictureText.setTextColor(resources.getColor(R.color.green))
            vyLoaded = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_3)

        photoButton = findViewById(R.id.photo_button)
        vyLoadButton = findViewById(R.id.vy_load)
        vyLoad2Button = findViewById(R.id.vy_load2)
        passportLoadText = findViewById(R.id.pasport_load_picture_text)
        vyLoadPictureText = findViewById(R.id.pasport_load_picture_text2)

        dobEditText = findViewById(R.id.dob)
        vyNumberEditText = findViewById(R.id.vynumber)
        dobErrorText = findViewById(R.id.dob_error)
        vyNumberErrorText = findViewById(R.id.vynumber_error)
        vyLoadErrorText = findViewById(R.id.vy_load_error)
        pasportLoadErrorText = findViewById(R.id.pasport_error)


        photoButton.setOnClickListener {
            getImage.launch("image/*")
        }

        vyLoadButton.setOnClickListener {
            getImageVyLoad.launch("image/*")
        }

        vyLoad2Button.setOnClickListener {
            getImageVyLoad2.launch("image/*")
        }

        val chevronButton: ImageButton = findViewById(R.id.chevron_button)
        chevronButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        dobEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    dobEditText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        val nextButton: Button = findViewById(R.id.login_button)
        nextButton.setOnClickListener {
            if (validateFields()) {
                startActivity(Intent(this, CongratsActivity::class.java))
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        val dob = dobEditText.text.toString()
        if (dob.isEmpty() || !isValidDate(dob)) {
            dobErrorText.text = "Введите корректную дату (не завтра)"
            dobErrorText.visibility = TextView.VISIBLE
            dobEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            dobErrorText.visibility = TextView.GONE
            dobEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        val email = vyNumberEditText.text.toString()
        if (email.isEmpty() || !isValidEmail(email)) {
            vyNumberErrorText.visibility = TextView.VISIBLE
            vyNumberEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            vyNumberErrorText.visibility = TextView.GONE
            vyNumberEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        if (!vyLoaded) {
            vyLoadErrorText.visibility = TextView.VISIBLE
            isValid = false
        } else{
            vyLoadErrorText.visibility = TextView.GONE
        }

        if (!pasportLoaded) {
            pasportLoadErrorText.visibility = TextView.VISIBLE
            isValid = false
        } else {
            pasportLoadErrorText.visibility = TextView.GONE
        }

        return isValid
    }

    private fun isValidDate(dateString: String): Boolean {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val inputDate = sdf.parse(dateString)
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        return inputDate.before(tomorrow)
    }
}

