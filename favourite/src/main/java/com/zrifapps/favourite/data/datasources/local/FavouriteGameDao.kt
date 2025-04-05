package com.zrifapps.favourite.data.datasources.local


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zrifapps.favourite.domain.entity.FavouriteGame
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: FavouriteGame)

    @Delete
    suspend fun delete(game: FavouriteGame)

    @Query("SELECT * FROM favourite_games")
    fun getAll(): Flow<List<FavouriteGame>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_games WHERE id = :id)")
    suspend fun isFavourite(id: Int): Boolean
}

