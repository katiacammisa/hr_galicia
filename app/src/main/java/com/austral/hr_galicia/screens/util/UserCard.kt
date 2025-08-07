package com.austral.hr_galicia.screens.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.austral.hr_galicia.data.Favorite

@Composable
fun UserCardUI(
    thumbnail: String,
    fullName: String,
    country: String,
    email: String,
    age: String,
    modifier: Modifier = Modifier,
    isInFavorites: Boolean = false,
    swapFavoriteState: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth().background(color = Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            SubcomposeAsyncImage(
                model = thumbnail,
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                loading = {
                    Loader()
                },
                modifier = Modifier.background(Color.Gray, shape = CircleShape)
            )
            Spacer(Modifier.size(10.dp))
            Column {
                Text(fullName)
                Text(country)
                Text(email)
                Text("$age years")
            }
        }
        IconButton(onClick = swapFavoriteState) {
            Icon(
                if (isInFavorites) {
                    Icons.Filled.Delete
                } else {
                    Icons.Outlined.Add
                },
                contentDescription = "favorite",
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewUserCardUI() {
    UserCardUI(
        thumbnail = user.thumbnailURL,
        fullName = user.fullName,
        country = user.country,
        email = user.email,
        age = user.age,
    )
}