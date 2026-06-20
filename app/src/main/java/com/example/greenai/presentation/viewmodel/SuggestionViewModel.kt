package com.example.greenai.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.domain.repository.Repository
import com.example.greenai.ui.screen.FieldData
import com.example.greenai.utils.SuggestionMode
import com.example.greenai.ui.state.Resource
import com.example.greenai.ui.state.SuggestionResult
import com.example.greenai.utils.mapToCropRequest
import com.example.greenai.utils.mapToFertilizerRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SuggestionViewModel(
    val repo: Repository
): ViewModel(){

    private val _uiState = MutableStateFlow<Resource<SuggestionResult>>(Resource.Idle())
    val uiState = _uiState.asStateFlow()

    var selectedMode by mutableStateOf(SuggestionMode.FERTILIZER)
        private set

    fun setMode(mode: SuggestionMode) {
        selectedMode = mode
    }

    fun recommend(values: List<String>, fields: List<FieldData>) {

        viewModelScope.launch {
            _uiState.value = Resource.Loading()

            try {

                when (selectedMode) {

                    SuggestionMode.FERTILIZER -> {
                        val request = mapToFertilizerRequest(values, fields)
                        val result = repo.recommendFertilizer(request)
                        _uiState.value = Resource.Success(
                            data = SuggestionResult.Fertilizer(result.recommendedFertilizer)
                        )
                    }

                    SuggestionMode.CROP -> {
                        val request = mapToCropRequest(values, fields)
                        val result = repo.recommendAll(request)
                        _uiState.value = Resource.Success(
                            data = SuggestionResult.Crop(result.crop,result.fertilizer)
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.value = Resource.Error(
                    message = e.message ?: "Unknown error"
                )
            }
        }
    }
}