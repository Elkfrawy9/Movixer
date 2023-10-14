package com.elkfrawy.movixer.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val height: Int,
    val width: Int,
    val file_path: String,
):Parcelable
