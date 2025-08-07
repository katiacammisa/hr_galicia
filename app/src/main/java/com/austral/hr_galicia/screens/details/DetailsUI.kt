package com.austral.hr_galicia.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.screens.util.Loader

@Composable
fun Details(
    user: User,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            SubcomposeAsyncImage(
                model = user.imageHQURL,
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                loading = {
                    Loader()
                },
                modifier = Modifier.background(Color.Gray, shape = CircleShape)
            )
        }
        Text(user.fullName)
        Text(user.address)
        Text(user.dateOfBirth)
        Text(user.phoneNumber)
    }
}