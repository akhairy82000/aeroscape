package com.aeroscape.wallpapers.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aeroscape.wallpapers.ui.screens.FavoritesScreen
import com.aeroscape.wallpapers.ui.screens.GalleryScreen
import com.aeroscape.wallpapers.ui.screens.PreviewScreen
import com.aeroscape.wallpapers.ui.viewmodel.WallpaperViewModel

private object Routes {
    const val GALLERY = "gallery"
    const val FAVORITES = "favorites"
    const val PREVIEW = "preview/{id}"
    fun preview(id: String) = "preview/$id"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: WallpaperViewModel = viewModel()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute == Routes.GALLERY || currentRoute == Routes.FAVORITES

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (showBottomBar) {
                AeroBottomBar(currentRoute = currentRoute) { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.GALLERY,
            modifier = androidx.compose.ui.Modifier.padding(
                bottom = if (showBottomBar) padding.calculateBottomPadding() else 0.dp
            )
        ) {
            composable(Routes.GALLERY) {
                GalleryScreen(viewModel) { spec ->
                    navController.navigate(Routes.preview(spec.id))
                }
            }
            composable(Routes.FAVORITES) {
                FavoritesScreen(viewModel) { spec ->
                    navController.navigate(Routes.preview(spec.id))
                }
            }
            composable(
                route = Routes.PREVIEW,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("id") ?: return@composable
                PreviewScreen(
                    wallpaperId = id,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun AeroBottomBar(currentRoute: String?, onNavigate: (String) -> Unit) {
    NavigationBar(containerColor = Color.Black.copy(alpha = 0.18f)) {
        NavigationBarItem(
            selected = currentRoute == Routes.GALLERY,
            onClick = { onNavigate(Routes.GALLERY) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Explore") },
            label = { Text("Explore") },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.White.copy(alpha = 0.22f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Routes.FAVORITES,
            onClick = { onNavigate(Routes.FAVORITES) },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.White.copy(alpha = 0.22f)
            )
        )
    }
}
