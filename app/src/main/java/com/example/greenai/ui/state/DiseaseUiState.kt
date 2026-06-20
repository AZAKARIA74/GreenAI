package com.example.greenai.ui.state

import android.net.Uri
import com.example.greenai.domain.model.DiseaseResponse

data class DiseaseUiState(
    val imageUri: Uri? = null,
    val result: Resource<DiseaseResponse> = Resource.Loading()
)