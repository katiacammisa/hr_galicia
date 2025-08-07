package com.austral.hr_galicia.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.data.toFavorite
import com.austral.hr_galicia.screens.util.Loader
import com.austral.hr_galicia.screens.util.UserCardUI
import java.util.Locale

@Composable
fun MainMenu(
    onClickOfUser: (User) -> Unit,
) {
    val viewModel = hiltViewModel<MainMenuViewModel>()
    val users by viewModel.users.collectAsStateWithLifecycle()
    val showRetry by viewModel.showRetry.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle(listOf())
    val pageNumber by viewModel.pageNumber.collectAsStateWithLifecycle()

    var query by remember {
        mutableStateOf("")
    }

    val filteredUsers by remember {
        derivedStateOf { users.filter { it.nationality.lowercase(Locale.getDefault())
            .contains(query.lowercase(Locale.getDefault())) } }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        if (loading) {
            Loader()
        } else if (showRetry) {
            Text(
                "There was an error"
            )
            Button(
                onClick = viewModel::retryApiCall
            ) {
                Text(
                    "Retry"
                )
            }
        } else {
            SearchBox(
                query = query,
                onQueryChange = {
                    query = it
                },
            )
            filteredUsers.forEach { user ->
                val isInFavorites = favorites.any { it.id == user.id }
                UserCardUI(
                    thumbnail = user.thumbnailURL,
                    fullName = user.fullName,
                    country = user.country,
                    email = user.email,
                    age = user.age,
                    isInFavorites = isInFavorites,
                    swapFavoriteState = {
                        viewModel.swapFavoriteState(user.toFavorite(), isInFavorites)
                    },
                    modifier = Modifier.clickable {
                        onClickOfUser(user)
                    }
                )
            }
            Paging(
                onBack = viewModel::onBack,
                onForward = viewModel::onForward,
                pageNumber = pageNumber,
            )
        }
    }
}