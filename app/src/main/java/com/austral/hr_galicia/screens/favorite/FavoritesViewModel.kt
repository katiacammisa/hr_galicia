package com.austral.hr_galicia.screens.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.austral.hr_galicia.apiManager.ApiServiceImpl
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.storage.HRGaliciaDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val favoritesManager: FavoritesManager,
): ViewModel() {

    private val database = HRGaliciaDatabase.getDatabase(context)

    val favorites = database.favoriteDao().getAllFavorites().asFlow()

    fun swapFavoriteState(favorite: Favorite, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                favoritesManager.removeFromFavorites(favorite)
            } else {
                favoritesManager.addToFavorites(favorite)
            }
        }
    }
}