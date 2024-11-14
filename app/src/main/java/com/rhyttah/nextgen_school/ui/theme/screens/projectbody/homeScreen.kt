package com.rhyttah.nextgen_school.ui.theme.screens.projectbody

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.nextgen_school.R

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .background(Color.White)

            ) {
                Image(
                    painter = painterResource(R.drawable.blacklogo),
                    contentDescription = "Circle Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(5.dp, Color.Gray, CircleShape)//optional

                )
                Text(
                    text = "NextGen Coders Club",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Nextgen Coders Club is a coding program designed to introduce kids to the world of technology and programming. The club offers coding classes aimed at nurturing creativity, problem-solving skills, and logical thinking in young minds. " +
                            "Held on Saturdays, these sessions are an engaging and interactive way for kids to learn essential tech skills early, preparing them for a future driven by technology.",
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}