package com.example.greenai.domain.model

import com.google.gson.annotations.SerializedName

data class DiseaseResponse(
    @SerializedName("disease")
    val prediction: String,
    val confidence: Float
)