package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.models.Model

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val index = intent.getIntExtra("student_index", -1)
        if (index == -1) return

        val student = Model.shared.students[index]

        val nameEditText: EditText = findViewById(R.id.new_student_edit_name_text_view)
        val idEditText: EditText = findViewById(R.id.new_student_edit_id_text_view)
        val phoneEditText: EditText = findViewById(R.id.new_student_edit_phone_text_view)
        val addressEditText: EditText = findViewById(R.id.new_student_edit_address_text_view)
        val checkBox: CheckBox = findViewById(R.id.new_student_checkBox)
        val avatarImageView: ImageView = findViewById(R.id.student_details_image_view)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        checkBox.isChecked = student.isChecked
        avatarImageView.setImageResource(R.drawable.profile)

        val saveButton: Button = findViewById(R.id.student_details_edit_button)
        val deleteButton: Button = findViewById(R.id.student_details_delete_button)
        val cancelButton: Button = findViewById(R.id.student_details_cancel_button)

        saveButton.setOnClickListener {
            val updatedStudent = student.copy(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkBox.isChecked
            )

            Model.shared.updateStudent(index, updatedStudent)
            navigateToStudentList()
        }


        deleteButton.setOnClickListener {
            Model.shared.deleteStudent(index)
            navigateToStudentList()
        }

        cancelButton.setOnClickListener {
            navigateToStudentList()
        }


    }
    private fun navigateToStudentList() {
        val intent = Intent(this, StudentsListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}