package com.example.drivenext.registration

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.app.DatePickerDialog
import android.widget.TextView
import com.example.drivenext.BaseActivity
import com.example.drivenext.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SecondRegistrationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_2)

        // Инициализация полей
        val chevronButton: ImageButton = findViewById(R.id.chevron_button)
        val dobEditText: EditText = findViewById(R.id.dob)

        val nextButton: Button = findViewById(R.id.login_button)

        // Логика для кнопки назад
        chevronButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Логика для выбора даты рождения
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

        // Логика для кнопки "Далее"
        nextButton.setOnClickListener {
            if (validateFields()) {
                startActivity(Intent(this, ThirdRegistrationActivity::class.java))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateFields(): Boolean {
        var isValid = true

        val lastNameEditText: EditText = findViewById(R.id.last_name)
        val firstNameEditText: EditText = findViewById(R.id.first_name)
        val middleNameEditText: EditText = findViewById(R.id.middle_name)
        val dobEditText: EditText = findViewById(R.id.dob)

        val lastNameErrorTextView: TextView = findViewById(R.id.last_name_error)
        val firstNameErrorTextView: TextView = findViewById(R.id.first_name_error)
        val middleNameErrorTextView: TextView = findViewById(R.id.middle_name_error)
        val dobErrorTextView: TextView = findViewById(R.id.dob_error)
        val genderErrorTextView: TextView = findViewById(R.id.gender_name_error)

        // Сброс ошибок
        lastNameErrorTextView.visibility = TextView.GONE
        firstNameErrorTextView.visibility = TextView.GONE
        middleNameErrorTextView.visibility = TextView.GONE
        dobErrorTextView.visibility = TextView.GONE
        genderErrorTextView.visibility = TextView.GONE

        // Валидация фамилии
        if (TextUtils.isEmpty(lastNameEditText.text.toString())) {
            lastNameErrorTextView.visibility = TextView.VISIBLE
            lastNameEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            lastNameErrorTextView.visibility = TextView.GONE
            lastNameEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        // Валидация имени
        if (TextUtils.isEmpty(firstNameEditText.text.toString())) {
            firstNameErrorTextView.visibility = TextView.VISIBLE
            firstNameEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            firstNameErrorTextView.visibility = TextView.GONE
            firstNameEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        // Валидация отчества
        if (TextUtils.isEmpty(middleNameEditText.text.toString())) {
            middleNameErrorTextView.visibility = TextView.VISIBLE
            middleNameEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            middleNameErrorTextView.visibility = TextView.GONE
            middleNameEditText.setBackgroundResource(R.drawable.edit_text_background)
        }

        // Валидация даты рождения
        if (TextUtils.isEmpty(dobEditText.text.toString())) {
            dobErrorTextView.visibility = TextView.VISIBLE
            dobEditText.setBackgroundResource(R.drawable.edit_text_error_background)
            isValid = false
        } else {
            dobErrorTextView.visibility = TextView.GONE
            dobEditText.setBackgroundResource(R.drawable.edit_text_background)

            dobErrorTextView.visibility = TextView.GONE
            dobEditText.setBackgroundResource(R.drawable.edit_text_background)

            if (!isValidAge(dobEditText.text.toString())) {
                dobErrorTextView.text = "Вы должны быть старше 18 лет"
                dobErrorTextView.visibility = TextView.VISIBLE
                dobEditText.setBackgroundResource(R.drawable.edit_text_error_background)
                isValid = false
            }
        }

        // Валидация RadioGroup (Пол)
        val genderRadioGroup: RadioGroup = findViewById(R.id.gender_radio_group)
        val selectedGenderId = genderRadioGroup.checkedRadioButtonId

        if (selectedGenderId == -1) {  // Если ничего не выбрано
            genderErrorTextView.visibility = TextView.VISIBLE
            isValid = false
        } else {
            genderErrorTextView.visibility = TextView.GONE
        }

        return isValid
    }

    // Функция для проверки возраста (старше 18 лет)
    private fun isValidAge(dobString: String): Boolean {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val inputDate = sdf.parse(dobString)

        val currentCalendar = Calendar.getInstance()
        val currentDate = currentCalendar.time

        val eighteenYearsAgo = Calendar.getInstance()
        eighteenYearsAgo.add(Calendar.YEAR, -18)
        val eighteenYearsAgoDate = eighteenYearsAgo.time

        // Проверяем, что дата рождения старше 18 лет
        return inputDate.before(eighteenYearsAgoDate) && !inputDate.after(currentDate)
    }
}
