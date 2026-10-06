package com.example.studentprofilemanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilemanager.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private var originalStudent: Student? = null

    companion object {

        private const val EXTRA_STUDENT = "STUDENT"

        private const val EXTRA_UPDATED = "UPDATED"
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding =
            ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        originalStudent =
            intent.getSerializableExtra(
                EXTRA_STUDENT
            ) as? Student

        originalStudent?.let {

            binding.edtStudentId.setText(it.id)

            binding.edtName.setText(it.name)

            binding.edtClass.setText(it.className)

            binding.edtEmail.setText(it.email)

            binding.edtPhone.setText(it.phone)

            binding.edtGpa.setText(
                it.gpa.toString()
            )
        }

        binding.btnSave.setOnClickListener {

            saveStudent()
        }

        binding.btnCancel.setOnClickListener {

            finish()
        }
    }


    private fun saveStudent() {

        val studentId =
            binding.edtStudentId.text
                .toString()
                .trim()


        val name =
            binding.edtName.text
                .toString()
                .trim()


        val className =
            binding.edtClass.text
                .toString()
                .trim()


        val email =
            binding.edtEmail.text
                .toString()
                .trim()


        val phone =
            binding.edtPhone.text
                .toString()
                .trim()


        val gpa =
            binding.edtGpa.text
                .toString()
                .toDoubleOrNull()

        if (studentId.isEmpty()) {

            Toast.makeText(
                this,
                "Vui lòng nhập MSSV!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (name.isEmpty()) {

            Toast.makeText(
                this,
                "Vui lòng nhập họ tên!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (className.isEmpty()) {

            Toast.makeText(
                this,
                "Vui lòng nhập lớp!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (
            email.isEmpty() ||
            !Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()
        ) {

            Toast.makeText(
                this,
                "Email không hợp lệ!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (phone.isEmpty()) {

            Toast.makeText(
                this,
                "Vui lòng nhập số điện thoại!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (
            gpa == null ||
            gpa !in 0.0..4.0
        ) {

            Toast.makeText(
                this,
                "GPA phải từ 0.0 đến 4.0!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val updatedStudent =
            Student(
                id = studentId,
                name = name,
                className = className,
                email = email,
                phone = phone,
                gpa = gpa
            )

        val resultIntent =
            Intent()

        resultIntent.putExtra(
            EXTRA_UPDATED,
            updatedStudent
        )

        setResult(
            Activity.RESULT_OK,
            resultIntent
        )

        finish()
    }
}