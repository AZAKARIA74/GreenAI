package com.example.greenai.domain.model

import com.google.gson.annotations.SerializedName

data class RecommendRequest(
    @SerializedName("Nitrogen")
    val nitrogen: Float,

    @SerializedName("Phosphorus")
    val phosphorus: Float,

    @SerializedName("Potassium")
    val potassium: Float,

    @SerializedName("pH")
    val pH: Float,

    @SerializedName("Rainfall")
    val rainfall: Float,

    @SerializedName("Temperature")
    val temperature: Float,

    @SerializedName("Soil_color")
    val soilColor: String
)