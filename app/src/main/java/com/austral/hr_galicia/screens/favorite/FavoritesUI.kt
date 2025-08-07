package com.austral.hr_galicia.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.data.toUser
import com.austral.hr_galicia.screens.util.UserCardUI

@Composable
fun Favorites(
    onClickOfUser: (User) -> Unit,
) {
    val viewModel = hiltViewModel<FavoritesViewModel>()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle(listOf())

    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text("Favorites", fontSize = 30.sp)
        Spacer(Modifier.size(10.dp))
        if (favorites.isNotEmpty()) {
            favorites.forEach {
                UserCardUI(
                    thumbnail = it.thumbnailURL,
                    fullName = it.fullName,
                    country = it.country,
                    email = it.email,
                    age = it.age,
                    swapFavoriteState = {
                        viewModel.swapFavoriteState(it, true)
                    },
                    modifier = Modifier.clickable {
                        onClickOfUser(it.toUser())
                    },
                    isInFavorites = true,
                )
            }
        } else {
            Text("You don't have any favorites yet")
        }
    }
}