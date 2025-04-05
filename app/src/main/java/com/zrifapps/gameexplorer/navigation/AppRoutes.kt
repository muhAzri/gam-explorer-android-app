package com.zrifapps.gameexplorer.navigation

import kotlinx.serialization.Serializable

interface AppRoute

@Serializable
object GameList : AppRoute

@Serializable
data class GameDetail(val gameId: String) : AppRoute

@Serializable
object FavoriteGames : AppRoute
