package com.android.ranit.cookflix.data.model.response

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("Title")
    val title: String,
    val image: String,
    val ingredient_list: List<String>
)