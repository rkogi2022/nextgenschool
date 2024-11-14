package com.rhyttah.nextgen_school.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.rhyttah.nextgen_school.R
import com.rhyttah.nextgen_school.navigation.ROUTE_HOME
import com.rhyttah.nextgen_school.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController) { // Pass navController here
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var mypass by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)

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
        Text(text = "NextGen Coders Club",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Enter Email....",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = mypass,
            onValueChange = { mypass = it },
            label = {
                Text(
                    text = "Enter Password....",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                // Handle login logic here
                navController.navigate(ROUTE_HOME) // Navigate to home on successful login
            },
            modifier = Modifier.width(300.dp)
        ) {
            Text(
                text = "Login",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp
            )
        }

        TextButton(onClick = {
            navController.navigate(ROUTE_REGISTER) // Navigate to RegisterScreen
        }) {
            Text(
                text = "I don't have an account. Click here to Register",
                color = Color.Red,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
fun LoginPrev() {
    LoginScreen(navController = rememberNavController())
}