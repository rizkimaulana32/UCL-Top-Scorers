package com.example.ucltopscorers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopScore(
    val img: Int,
    val name: String,
    val goal: Int,
    val description: String
) : Parcelable
