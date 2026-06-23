package com.example.greenai.domain.model

import com.google.gson.annotations.SerializedName

data class RecommendResponse(
    @SerializedName("recommended_crop")
    val crop: String,

    @SerializedName("recommended_fertilizer")
    val fertilizer: String
)