package com.austral.hr_galicia.data

import android.os.Parcel
import android.os.Parcelable

data class User(
    val id: String,
    val fullName: String,
    val address: String,
    val thumbnailURL: String,
    val imageHQURL: String,
    val country: String,
    val nationality: String,
    val dateOfBirth: String,
    val age: String,
    val phoneNumber: String,
    val email: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(id)
        p0.writeString(fullName)
        p0.writeString(address)
        p0.writeString(thumbnailURL)
        p0.writeString(imageHQURL)
        p0.writeString(country)
        p0.writeString(nationality)
        p0.writeString(dateOfBirth)
        p0.writeString(age)
        p0.writeString(phoneNumber)
        p0.writeString(email)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

fun User.toFavorite(): Favorite =
    Favorite(
        id = id,
        thumbnailURL = thumbnailURL,
        imageHQURL = imageHQURL,
        country = country,
        nationality = nationality,
        dateOfBirth = dateOfBirth,
        age = age,
        phoneNumber = phoneNumber,
        email = email,
        fullName = fullName,
        address = address,
    )
