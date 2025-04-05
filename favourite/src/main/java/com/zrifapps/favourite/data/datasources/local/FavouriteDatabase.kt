package com.zrifapps.favourite.data.datasources.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.zrifapps.favourite.domain.entity.FavouriteGame

@Database(
    entities = [FavouriteGame::class],
    version = 1,
    exportSchema = false
)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteGameDao(): FavouriteGameDao
}
