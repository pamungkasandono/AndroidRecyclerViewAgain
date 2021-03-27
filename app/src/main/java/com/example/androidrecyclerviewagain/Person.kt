package com.example.androidrecyclerviewagain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    var name: String,
    var desc: String,
    var photo: String
) : Parcelable