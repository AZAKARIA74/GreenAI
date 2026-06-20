package com.example.greenai.domain.model

data class DiseaseResponse(
    val prediction: String,
    val confidence: Float
)