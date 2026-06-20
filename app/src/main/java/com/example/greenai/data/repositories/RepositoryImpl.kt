package com.example.greenai.data.repositories

import com.example.greenai.data.remote.Api
import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.domain.model.FertilizerRequest
import com.example.greenai.domain.model.FertilizerResponse
import com.example.greenai.domain.model.RecommendRequest
import com.example.greenai.domain.model.RecommendResponse
import com.example.greenai.domain.repository.Repository
import okhttp3.MultipartBody

class RepositoryImpl(
    private val api: Api
) : Repository {

    override suspend fun predictDisease(
        cropImg: MultipartBody.Part
    ): DiseaseResponse {

        return api.predictDisease(cropImg)
    }

    override suspend fun recommendAll(
        request: RecommendRequest
    ): RecommendResponse {

        return api.recommendAll(request)
    }

    override suspend fun recommendFertilizer(
        request: FertilizerRequest
    ): FertilizerResponse {

        return api.recommendFertilizer(request)
    }
}