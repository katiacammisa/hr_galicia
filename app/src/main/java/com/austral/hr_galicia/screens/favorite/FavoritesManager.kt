package com.austral.hr_galicia.screens.favorite

import android.content.Context
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.storage.HRGaliciaDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FavoritesManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val database = HRGaliciaDatabase.getDatabase(context)

    suspend fun addToFavorites(favorite: Favorite) {
        database.favoriteDao().insert(favorite)
    }

    suspend fun removeFromFavorites(favorite: Favorite) {
        database.favoriteDao().deleteById(favorite.id)
    }
}