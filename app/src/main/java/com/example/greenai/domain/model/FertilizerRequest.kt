package com.example.greenai.domain.model

data class FertilizerRequest(
    val cropType: String,
    val nitrogen: Float,
    val phosphorus: Float,
    val potassium: Float,
    val temperature: Float,
    val humidity: Float,
    val pH: Float,
    val soilType: String,
)