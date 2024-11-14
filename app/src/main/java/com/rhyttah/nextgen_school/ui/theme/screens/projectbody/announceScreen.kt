package com.rhyttah.nextgen_school.ui.theme.screens.projectbody

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.nextgen_school.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnnounceScreen(navController: NavController) {
    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White) // Set background color if needed
            ) {
                Image(
                    painter = painterResource(R.drawable.announce),
                    contentDescription = "Full-page Image",
                    modifier = Modifier.fillMaxSize() // Occupy the full size of the screen
                )
            }
        }
    )
}

@Preview
@Composable
fun AnnouncePrev() {
    AnnounceScreen(rememberNavController())
}

