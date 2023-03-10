package com.example.apprickymorty.apirest.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var origin: LocationData,
    var location: LocationData,
    var image: String,
    var episode: List<String>
): Parcelable
