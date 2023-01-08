package com.android.ranit.cookflix.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("Title")
    val title: String,
    val image: String,
    val ingredient_list: List<String>
): Parcelable