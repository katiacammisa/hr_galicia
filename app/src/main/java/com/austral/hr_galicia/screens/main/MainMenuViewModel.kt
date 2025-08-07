package com.austral.hr_galicia.screens.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.austral.hr_galicia.apiManager.ApiServiceImpl
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.screens.favorite.FavoritesManager
import com.austral.hr_galicia.storage.HRGaliciaDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl,
    private val favoritesManager: FavoritesManager,
): ViewModel() {
    private val database = HRGaliciaDatabase.getDatabase(context)

    val favorites = database.favoriteDao().getAllFavorites().asFlow()

    private var _users = MutableStateFlow(listOf<User>())
    val users = _users.asStateFlow()

    private var _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private var _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    private var _pageNumber = MutableStateFlow(1)
    val pageNumber = _pageNumber.asStateFlow()

    init {
        loadUsers(1)
    }

    fun retryApiCall() {
        loadUsers(_pageNumber.value)
    }

    fun swapFavoriteState(favorite: Favorite, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                favoritesManager.removeFromFavorites(favorite)
            } else {
                favoritesManager.addToFavorites(favorite)
            }
        }
    }

    fun onBack() {
        loadUsers(_pageNumber.value-1)
        _pageNumber.value -= 1
    }

    fun onForward() {
        loadUsers(_pageNumber.value+1)
        _pageNumber.value += 1
    }

    private fun loadUsers(pageNumber: Int) {
        val usersAux = mutableListOf<User>()
        (1 * pageNumber..10 * pageNumber).forEach {
            _loading.value = true
            apiServiceImpl.getUsers(
                context = context,
                onSuccess = { user ->
                    usersAux.add(user)
                    viewModelScope.launch {
                        _users.emit(usersAux)
                    }
                    _showRetry.value = false
                    _loading.value = false
                },
                onFail = {
                    _showRetry.value = true
                    _loading.value = false
                },
                page = it.toString(),
            )
        }
    }
}