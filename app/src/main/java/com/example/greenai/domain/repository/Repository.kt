package com.example.greenai.domain.repository

import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.domain.model.FertilizerRequest
import com.example.greenai.domain.model.FertilizerResponse
import com.example.greenai.domain.model.RecommendRequest
import com.example.greenai.domain.model.RecommendResponse
import okhttp3.MultipartBody

interface Repository {
    suspend fun predictDisease(
        cropImg: MultipartBody.Part
    ) : DiseaseResponse

    suspend fun recommendAll(
        request: RecommendRequest
    ) : RecommendResponse

    suspend fun recommendFertilizer(
        request: FertilizerRequest
    ) : FertilizerResponse
}