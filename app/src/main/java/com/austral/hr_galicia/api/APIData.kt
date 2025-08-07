package com.austral.hr_galicia.api

import com.austral.hr_galicia.data.User
import com.austral.hr_galicia.util.DateParser

fun UserResponse.toUser(): User =
    User(
        id = login.uuid,
        fullName = "${name.title} ${name.first} ${name.last}",
        thumbnailURL = picture.thumbnail,
        imageHQURL = picture.large,
        address = "${location.street.name} ${location.street.number}, " +
                "${location.postcode} ${location.city} ${location.state} ${location.country}",
        country = location.country,
        nationality = nat,
        dateOfBirth = DateParser.parseDate(dob.date),
        age = dob.age,
        phoneNumber = phone,
        email = email,
    )

data class RequestResponse(
    val results: List<UserResponse>,
)

data class UserResponse(
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val login: LoginResponse,
    val dob: DateOfBirthResponse,
    val phone: String,
    val picture: PictureResponse,
    val nat: String,
)

data class NameResponse(
    val title: String,
    val first: String,
    val last: String,
)

data class LocationResponse(
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)

data class StreetResponse(
    val number: String,
    val name: String,
)

data class LoginResponse(
    val uuid: String,
)

data class DateOfBirthResponse(
    val date: String,
    val age: String,
)

data class PictureResponse(
    val large: String,
    val thumbnail: String,
)