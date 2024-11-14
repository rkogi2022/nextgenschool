package com.rhyttah.nextgen_school.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.rhyttah.nextgen_school.R
import com.rhyttah.nextgen_school.navigation.ROUTE_LOGIN
import com.rhyttah.nextgen_school.ui.theme.screens.models.User

@Composable
fun RegisterScreen(navController: NavController) {
    val myname = remember { mutableStateOf("") }
    val myemail = remember { mutableStateOf("") }
    val myage = remember { mutableStateOf("") }
    val mypass = remember { mutableStateOf("") }

    // Adding vertical scroll
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set background color to white
            .padding(top = 100.dp) // Add top padding of 100.dp
            .verticalScroll(scrollState) // Enable vertical scrolling
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Circle Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Gray, CircleShape)
        )

        Text(
            text = "NextGen Coders Club",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            color = Color.Black // Set text color to black
        )

        Spacer(modifier = Modifier.height(20.dp)) // Reduced spacing

        OutlinedTextField(
            value = myname.value,
            onValueChange = { myname.value = it },
            label = {
                Text(
                    text = "Enter your Name....",
                    color = Color.Black, // Set text color to black
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(20.dp)) // Reduced spacing

        OutlinedTextField(
            value = myemail.value,
            onValueChange = { myemail.value = it },
            label = {
                Text(
                    text = "Enter Email....",
                    color = Color.Black, // Set text color to black
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(20.dp)) // Reduced spacing

        OutlinedTextField(
            value = myage.value,
            onValueChange = { myage.value = it },
            label = {
                Text(
                    text = "Enter your age....",
                    color = Color.Black, // Set text color to black
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(20.dp)) // Reduced spacing

        OutlinedTextField(
            value = mypass.value,
            onValueChange = { mypass.value = it },
            label = {
                Text(
                    text = "Enter Password....",
                    color = Color.Black, // Set text color to black
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(20.dp)) // Reduced spacing

        Button(
            onClick = {
                // Create a User object
                val user = User(myname.value, myemail.value, myage.value, mypass.value)

                // Get a reference to the Firebase Database
                val database = Firebase.database
                val usersRef = database.getReference("users")

                // Push user data to Firebase under a unique key
                val newUserRef = usersRef.push()

                newUserRef.setValue(user)
                    .addOnSuccessListener {
                        // Handle success (e.g., show a Toast or navigate back to the login screen)
                        navController.navigate(ROUTE_LOGIN)
                    }
                    .addOnFailureListener {
                        // Handle failure (e.g., show a Toast with error message)
                    }
            },
            modifier = Modifier
                .width(300.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Register",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp)) // Reduced spacing

        TextButton(
            onClick = {
                navController.navigate(ROUTE_LOGIN) // Navigate back to LoginScreen
            }
        ) {
            Text(
                text = "I already have an account. Click here to Login",
                color = Color.Red, // Set text color to red
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
private fun RegisterPrev() {
    RegisterScreen(navController = rememberNavController())
}
