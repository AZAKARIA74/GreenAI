package com.example.greenai.ui.state

sealed class SuggestionResult {
    data class Fertilizer(val fertilizer: String) : SuggestionResult()
    data class Crop(val crop: String, val fertilizer: String) : SuggestionResult()
}
