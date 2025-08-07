package com.austral.hr_galicia.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.austral.hr_galicia.data.Favorite
import com.austral.hr_galicia.data.FavoriteDao

@Database(entities = [Favorite::class], version = 1)
abstract class HRGaliciaDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: HRGaliciaDatabase? = null

        fun getDatabase(context: Context): HRGaliciaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HRGaliciaDatabase::class.java,
                    "hr_galicia_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}