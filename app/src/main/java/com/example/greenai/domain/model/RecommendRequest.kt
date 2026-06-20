package com.example.greenai.domain.model

data class RecommendRequest(
    val nitrogen: Float,
    val phosphorus: Float,
    val potassium: Float,
    val pH: Float,
    val rainfall: Float,
    val temperature: Float,
    val soilColor: String
)