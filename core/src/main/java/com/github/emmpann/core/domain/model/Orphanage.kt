package com.github.emmpann.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Orphanage(
    val name: String? = "",
    val thumbnail: String? = "",
) : Parcelable