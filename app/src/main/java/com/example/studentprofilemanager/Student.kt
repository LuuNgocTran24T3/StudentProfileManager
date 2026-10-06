package com.example.studentprofilemanager

import java.io.Serializable

data class Student(
    val id: String,
    var name: String,
    var className: String,
    var email: String,
    var phone: String,
    var gpa: Double
) : Serializable