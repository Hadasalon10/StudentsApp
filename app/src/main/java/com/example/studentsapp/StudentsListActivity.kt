package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.models.Model
import com.example.studentsapp.models.Student

class StudentsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val students = Model.shared.students

        val listView: ListView = findViewById(R.id.student_list_activity_list_view)
        listView.adapter = StudentListAdapter(
            students = students
        )
    }
    class StudentListAdapter(
        private val students: List<Student>
    ): BaseAdapter() {
        override fun getCount(): Int = students.size

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long = 0

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            val student = students[position]
            val inflator = LayoutInflater.from(parent?.context)
            val view = convertView ?:inflator.inflate(R.layout.student_row_layout, parent, false)

            val nameTextView: TextView = view.findViewById(R.id.students_row_name_text_view)
            val idTextView: TextView = view.findViewById(R.id.students_row_id_text_view)

            nameTextView.text = student.name
            idTextView.text = student.id

            return view
        }
    }
}