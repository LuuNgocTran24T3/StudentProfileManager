package com.example.studentprofilemanager

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    companion object {

        private const val TAG = "TAG_LIFECYCLE"

        private const val EXTRA_STUDENT = "STUDENT"

        private const val EXTRA_UPDATED = "UPDATED"
    }

    private var student = Student(

        name = "Lưu Ngọc Trân",
        id = "2415053122343",
        className = "24T3",
        email = "2415053122343",
        phone = "0334230808",
        gpa = 3.0
    )

    private lateinit var editLauncher:
            ActivityResultLauncher<Intent>

    private lateinit var galleryLauncher:
            ActivityResultLauncher<String>

    private lateinit var cameraLauncher:
            ActivityResultLauncher<String>


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        binding =
            ActivityMainBinding.inflate(
                layoutInflater
            )

        setContentView(binding.root)

        registerLaunchers()

        bindData(student)

        binding.btnEditProfile.setOnClickListener {

            val intent =
                Intent(
                    this,
                    EditProfileActivity::class.java
                )


            intent.putExtra(
                EXTRA_STUDENT,
                student
            )


            editLauncher.launch(intent)
        }

        binding.btnChangeAvatar.setOnClickListener {

            galleryLauncher.launch(
                "image/*"
            )
        }

        binding.btnCallHotline.setOnClickListener {

            callStudent(
                student.phone
            )
        }

        binding.btnRequestCamera.setOnClickListener {

            cameraLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    private fun registerLaunchers() {

        editLauncher =
            registerForActivityResult(
                ActivityResultContracts
                    .StartActivityForResult()
            ) { result ->


                if (
                    result.resultCode ==
                    Activity.RESULT_OK
                ) {


                    val updatedStudent =
                        result.data
                            ?.getSerializableExtra(
                                EXTRA_UPDATED
                            ) as? Student


                    updatedStudent?.let {

                        // Cập nhật Student

                        student = it


                        // Hiển thị lại dữ liệu

                        bindData(student)


                        Toast.makeText(
                            this,
                            "Đã lưu thông tin thành công!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        galleryLauncher =
            registerForActivityResult(
                ActivityResultContracts
                    .GetContent()
            ) { uri ->


                uri?.let {

                    binding.imgAvatar
                        .setImageURI(it)


                    Toast.makeText(
                        this,
                        "Đã đổi avatar!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        cameraLauncher =
            registerForActivityResult(
                ActivityResultContracts
                    .RequestPermission()
            ) { granted ->


                if (granted) {

                    Toast.makeText(
                        this,
                        "Đã cấp quyền Camera!",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Toast.makeText(
                        this,
                        "Bị từ chối quyền Camera!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun bindData(
        student: Student
    ) {


        // Họ tên

        binding.tvName.text =
            student.name


        // MSSV

        binding.tvStudentId.text =
            "MSSV: ${student.id}"


        // Lớp

        binding.tvClass.text =
            "Lớp: ${student.className}"


        // Email

        binding.tvEmail.text =
            "Email: ${student.email}"


        // Số điện thoại

        binding.tvPhone.text =
            "SĐT: ${student.phone}"


        // GPA

        binding.tvGpaBadge.text =
            "GPA: %.2f".format(
                student.gpa
            )
    }

    private fun callStudent(
        phone: String
    ) {


        val intent =
            Intent(
                Intent.ACTION_DIAL
            )


        intent.data =
            Uri.parse(
                "tel:$phone"
            )


        try {

            startActivity(intent)

        } catch (
            e: ActivityNotFoundException
        ) {

            Toast.makeText(
                this,
                "Không tìm thấy ứng dụng gọi điện!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {

        super.onStart()

        Log.d(
            TAG,
            "onStart"
        )
    }


    override fun onResume() {

        super.onResume()

        Log.d(
            TAG,
            "onResume"
        )
    }


    override fun onPause() {

        super.onPause()

        Log.d(
            TAG,
            "onPause"
        )
    }


    override fun onStop() {

        super.onStop()

        Log.d(
            TAG,
            "onStop"
        )
    }


    override fun onRestart() {

        super.onRestart()

        Log.d(
            TAG,
            "onRestart"
        )
    }


    override fun onDestroy() {

        super.onDestroy()

        Log.d(
            TAG,
            "onDestroy"
        )
    }
}