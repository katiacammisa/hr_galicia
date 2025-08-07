package com.austral.hr_galicia.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Update
    suspend fun update(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("DELETE FROM favorite where id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): LiveData<List<Favorite>>
}