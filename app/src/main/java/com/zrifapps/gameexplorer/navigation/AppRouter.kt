package com.zrifapps.gameexplorer.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zrifapps.game.presentation.screens.GameListScreen
import com.zrifapps.game.presentation.screens.detail.GameDetailScreen


@Composable
fun AppRouter(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppRoute,
) {
    fun navigateTo(route: AppRoute, clearBackStack: Boolean = false) {
        navController.navigate(route) {
            if (clearBackStack) {
                popUpTo(navController.graph.startDestinationRoute ?: "") { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<GameList> {
            GameListScreen(
                onGameClicked = { gameId ->
                    navigateTo(GameDetail(gameId))
                },
                onFavoritesClicked = {
                    navigateTo(FavoriteGames)
                }
            )
        }

        composable<GameDetail> { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId") ?: return@composable
            GameDetailScreen(
                gameId = gameId,
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
