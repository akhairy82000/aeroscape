package com.aeroscape.wallpapers.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aeroscape.wallpapers.data.WallpaperCatalog
import com.aeroscape.wallpapers.data.WallpaperCategory
import com.aeroscape.wallpapers.data.WallpaperSpec
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val PREFS_NAME = "aeroscape_prefs"
private const val KEY_FAVORITES = "favorite_ids"

class WallpaperViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val allWallpapers: List<WallpaperSpec> = WallpaperCatalog.all

    private val _favorites = MutableStateFlow(loadFavorites())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _selectedCategory = MutableStateFlow<WallpaperCategory?>(null)
    val selectedCategory: StateFlow<WallpaperCategory?> = _selectedCategory.asStateFlow()

    val filteredWallpapers: StateFlow<List<WallpaperSpec>> =
        _selectedCategory
            .map { category -> WallpaperCatalog.byCategory(category) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allWallpapers)

    val favoriteWallpapers: StateFlow<List<WallpaperSpec>> =
        _favorites
            .map { favIds -> allWallpapers.filter { it.id in favIds } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setCategory(category: WallpaperCategory?) {
        _selectedCategory.value = category
    }

    fun toggleFavorite(id: String) {
        val current = _favorites.value
        val updated = if (id in current) current - id else current + id
        _favorites.value = updated
        prefs.edit().putStringSet(KEY_FAVORITES, updated).apply()
    }

    fun isFavorite(id: String): Boolean = id in _favorites.value

    fun wallpaperById(id: String): WallpaperSpec? = WallpaperCatalog.byId(id)

    private fun loadFavorites(): Set<String> =
        prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
}
