package com.rhyttah.nextgen_school.ui.theme.screens.projectbody

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.rhyttah.nextgen_school.navigation.ROUTE_VIEW
import com.rhyttah.nextgen_school.ui.theme.screens.models.Student

@Composable
fun AddScreen(navController: NavController, studentList: MutableList<Student> ) {
    var name by remember { mutableStateOf("") }
    var htmlMarks by remember { mutableStateOf("") }
    var cssMarks by remember { mutableStateOf("") }
    var jsMarks by remember { mutableStateOf("") }
    var pythonMarks by remember { mutableStateOf("") }
    var javaMarks by remember { mutableStateOf("") }
    var cSharpMarks by remember { mutableStateOf("") }

    // Get a reference to your database
    val database = Firebase.database.reference

    Scaffold(
        topBar = { NavigationBarScreen(navController) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Add Student Marks", fontSize = 24.sp, color = Color.Black)

            TextField(value = name, onValueChange = { name = it }, label = { Text("Student Name") })
            TextField(value = htmlMarks, onValueChange = { htmlMarks = it }, label = { Text("HTML Marks") })
            TextField(value = cssMarks, onValueChange = { cssMarks = it }, label = { Text("CSS Marks") })
            TextField(value = jsMarks, onValueChange = { jsMarks = it }, label = { Text("JS Marks") })
            TextField(value = pythonMarks, onValueChange = { pythonMarks = it }, label = { Text("Python Marks") })
            TextField(value = javaMarks, onValueChange = { javaMarks = it }, label = { Text("Java Marks") })
            TextField(value = cSharpMarks, onValueChange = { cSharpMarks = it }, label = { Text("C# Marks") })

            Button(onClick = {
                val student = Student(
                    name = name,
                    htmlMarks = htmlMarks.toIntOrNull() ?: 0,
                    cssMarks = cssMarks.toIntOrNull() ?: 0,
                    jsMarks = jsMarks.toIntOrNull() ?: 0,
                    pythonMarks = pythonMarks.toIntOrNull() ?: 0,
                    javaMarks = javaMarks.toIntOrNull() ?: 0,
                    cSharpMarks = cSharpMarks.toIntOrNull() ?: 0
                )

                studentList.add(student)

                val studentId = database.child("students").push().key
                if (studentId != null) {
                    database.child("students").child(studentId).setValue(student)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate(ROUTE_VIEW)
                            } else {
                                task.exception?.let {
                                    println("Firebase Error: ${it.message}")
                                }
                            }
                        }
                }
            }) {
                Text("Submit")
            }
        }
    }
}

@Preview
@Composable
fun AddPrev() {
    val sampleList = remember { mutableListOf<Student>() }
    AddScreen(navController = rememberNavController(), studentList = sampleList)
}
