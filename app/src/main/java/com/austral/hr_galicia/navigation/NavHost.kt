package com.austral.hr_galicia.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.screens.details.Details
import com.austral.hr_galicia.screens.main.MainMenu
import com.austral.hr_galicia.screens.favorite.Favorites
import com.austral.hr_galicia.screens.util.user
import com.austral.hr_galicia.storage.HRGaliciaDatabase

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HRGaliciaScreen.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {
        composable(route = HRGaliciaScreen.Home.name) {
            MainMenu(
                onClickOfUser = { user ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
                    navController.navigate(HRGaliciaScreen.Details.name)
                }
            )
        }
        composable(route = HRGaliciaScreen.Favorite.name) {
            Favorites(
                onClickOfUser = { user ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
                    navController.navigate(HRGaliciaScreen.Details.name)
                }
            )
        }
        composable(route = HRGaliciaScreen.Details.name) {
            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            if (user != null) {
                Details(user)
            }
        }
    }
}