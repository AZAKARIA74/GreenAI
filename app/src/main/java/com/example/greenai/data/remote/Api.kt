package com.example.greenai.data.remote

import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.domain.model.FertilizerRequest
import com.example.greenai.domain.model.FertilizerResponse
import com.example.greenai.domain.model.RecommendRequest
import com.example.greenai.domain.model.RecommendResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Api {

    @Multipart
    @POST("predict-disease")
    suspend fun predictDisease(
        @Part file: MultipartBody.Part
    ): DiseaseResponse

    @POST("recommend-all")
    suspend fun recommendAll(
        @Body request: RecommendRequest
    ): RecommendResponse

    @POST("recommend-fertilizer")
    suspend fun recommendFertilizer(
        @Body request: FertilizerRequest
    ): FertilizerResponse
}