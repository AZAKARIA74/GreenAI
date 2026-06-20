package com.example.greenai.domain.model

data class FertilizerRequest(
    val nitrogen: Int,
    val phosphorus: Int,
    val potassium: Int,
    val temperature: Float,
    val humidity: Float,
    val moisture: Float,
    val soilType: String,
    val cropType: String
)