package com.example.greenai.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.domain.repository.Repository
import com.example.greenai.utils.SuggestionMode
import com.example.greenai.ui.state.Resource
import com.example.greenai.ui.state.SuggestionResult
import com.example.greenai.utils.FieldData
import com.example.greenai.utils.mapToCropRequest
import com.example.greenai.utils.mapToFertilizerRequest
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
                        Log.d("API_REQUEST", Gson().toJson(request))
                        val result = repo.recommendFertilizer(request)
                        _uiState.value = Resource.Success(
                            data = SuggestionResult.Fertilizer(result.recommendedFertilizer)
                        )
                    }

                    SuggestionMode.CROP -> {
                        val request = mapToCropRequest(values, fields)
                        Log.d("API_REQUEST", Gson().toJson(request))
                        val result = repo.recommendAll(request)
                        _uiState.value = Resource.Success(
                            data = SuggestionResult.Crop(result.crop,result.fertilizer)
                        )
                    }
                }

            }  catch (e: HttpException) {

                Log.e(
                    "HTTP_CODE",
                    e.code().toString()
                )

                Log.e(
                    "HTTP_MESSAGE",
                    e.message()
                        ?: "No Message"
                )

                Log.e(
                    "HTTP_BODY",
                    e.response()
                        ?.errorBody()
                        ?.string()
                        ?: "No Error Body"
                )

                _uiState.value = Resource.Error(
                    e.message() ?: "Server Error"
                )

            } catch (e: Exception) {

                Log.e(
                    "API_ERROR",
                    e.message ?: "Unknown Error",
                    e
                )

                _uiState.value = Resource.Error(
                    e.message ?: "Unknown Error"
                )
            }

        }
    }
}