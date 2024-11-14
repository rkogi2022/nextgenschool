package com.rhyttah.nextgen_school.ui.theme.screens.projectbody

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import com.rhyttah.nextgen_school.navigation.ROUTE_VIEW
import com.rhyttah.nextgen_school.ui.theme.screens.models.Student

@Composable
fun UpdateScreen(navController: NavController, studentList: MutableList<Student>) {
    var name by remember { mutableStateOf("") }
    var htmlMarks by remember { mutableStateOf("") }
    var cssMarks by remember { mutableStateOf("") }
    var jsMarks by remember { mutableStateOf("") }
    var pythonMarks by remember { mutableStateOf("") }
    var javaMarks by remember { mutableStateOf("") }
    var cSharpMarks by remember { mutableStateOf("") }
    var studentFound by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                Text("Update Student Marks", fontSize = 24.sp, color = Color.Black)

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Student Name", color = Color.Black) }
                )

                Button(onClick = {
                    // Find the student and pre-fill marks
                    val student = studentList.find { it.name.trim().equals(name.trim(), ignoreCase = true) }
                    if (student != null) {
                        htmlMarks = student.htmlMarks.toString()
                        cssMarks = student.cssMarks.toString()
                        jsMarks = student.jsMarks.toString()
                        pythonMarks = student.pythonMarks.toString()
                        javaMarks = student.javaMarks.toString()
                        cSharpMarks = student.cSharpMarks.toString()
                        studentFound = true
                        Log.d("StudentSearch", "Student found: $name")
                    } else {
                        studentFound = false
                        Log.d("StudentSearch", "Student not found: $name")
                    }
                }) {
                    Text("Find Student")
                }

                if (studentFound) {
                    TextField(value = htmlMarks, onValueChange = { htmlMarks = it }, label = { Text("HTML Marks") })
                    TextField(value = cssMarks, onValueChange = { cssMarks = it }, label = { Text("CSS Marks") })
                    TextField(value = jsMarks, onValueChange = { jsMarks = it }, label = { Text("JS Marks") })
                    TextField(value = pythonMarks, onValueChange = { pythonMarks = it }, label = { Text("Python Marks") })
                    TextField(value = javaMarks, onValueChange = { javaMarks = it }, label = { Text("Java Marks") })
                    TextField(value = cSharpMarks, onValueChange = { cSharpMarks = it }, label = { Text("C# Marks") })

                    Button(onClick = {
                        studentList.find { it.name == name }?.apply {
                            this.htmlMarks = htmlMarks.toIntOrNull() ?: this.htmlMarks
                            this.cssMarks = cssMarks.toIntOrNull() ?: this.cssMarks
                            this.jsMarks = jsMarks.toIntOrNull() ?: this.jsMarks
                            this.pythonMarks = pythonMarks.toIntOrNull() ?: this.pythonMarks
                            this.javaMarks = javaMarks.toIntOrNull() ?: this.javaMarks
                            this.cSharpMarks = cSharpMarks.toIntOrNull() ?: this.cSharpMarks

                            updateStudentInFirebase(this)
                        }
                        navController.navigate(ROUTE_VIEW)
                    }) {
                        Text("Update Marks")
                    }
                } else {
                    Text("Student not found. Please check the name and try again.", color = Color.Red)
                }
            }
        }
    )
}

// Firebase update function
fun updateStudentInFirebase(student: Student) {
    // Get a reference to the Firebase Realtime Database
    val database = FirebaseDatabase.getInstance()
    val studentsRef = database.getReference("students")

    // Assuming that 'students' is a collection where each student is identified by their name
    studentsRef.child(student.name).setValue(student).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Log.d("Firebase", "Student updated successfully")
        } else {
            Log.d("Firebase", "Failed to update student: ${task.exception?.message}")
        }
    }
}

@Preview
@Composable
fun UpdatePrev() {
    val sampleList = mutableListOf(
        Student("John Doe", 85, 90, 75, 80, 95, 88)
    )
    UpdateScreen(rememberNavController(), studentList = sampleList)
}
