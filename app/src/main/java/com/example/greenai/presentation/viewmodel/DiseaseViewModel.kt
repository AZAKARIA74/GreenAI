package com.example.greenai.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.domain.repository.Repository
import com.example.greenai.ui.state.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class DiseaseViewModel(
    val repo: Repository
) : ViewModel() {


    var imageUri by mutableStateOf<Uri?>(null)
        private set

    fun updateImageUri(uri: Uri?) {
        imageUri = uri
    }
    private val _uiState =
        MutableStateFlow<Resource<DiseaseResponse>>(Resource.Idle())

    val uiState = _uiState.asStateFlow()

    fun predictDisease(img: MultipartBody.Part){

        viewModelScope.launch {
            _uiState.value = Resource.Loading()
            try {
                val response = repo.predictDisease(img)
                _uiState.value = Resource.Success(response)
            }catch (e: Exception){
                _uiState.value = Resource.Error(
                    e.message ?: "Unknown Error"
                )
            }

        }
    }
}









