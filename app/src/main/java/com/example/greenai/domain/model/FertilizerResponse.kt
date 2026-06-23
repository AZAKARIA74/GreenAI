package com.example.greenai.domain.model

import com.google.gson.annotations.SerializedName

data class FertilizerResponse(
    @SerializedName("recommended_fertilizer")
    val recommendedFertilizer: String
)