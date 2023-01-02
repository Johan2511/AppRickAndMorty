package com.example.apprickymorty.apirest.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    var name: String
): Parcelable
