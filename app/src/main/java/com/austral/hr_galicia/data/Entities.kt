package com.austral.hr_galicia.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val id: String,
    val thumbnailURL: String,
    val imageHQURL: String,
    val country: String,
    val nationality: String,
    val dateOfBirth: String,
    val age: String,
    val phoneNumber: String,
    val email: String,
    val fullName: String,
    val address: String,
)

fun Favorite.toUser(): User =
    User(
        id = id,
        fullName = fullName,
        address = address,
        thumbnailURL = thumbnailURL,
        imageHQURL = imageHQURL,
        country = country,
        nationality = nationality,
        dateOfBirth = dateOfBirth,
        age = age,
        phoneNumber = phoneNumber,
        email = email,
    )