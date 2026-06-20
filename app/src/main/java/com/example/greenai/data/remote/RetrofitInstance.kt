package com.example.greenai.data.remote

object RetrofitInstance {

    private const val BASE_URL = "https://web-production-994b6.up.railway.app/"

    val api: Api by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create()
            )
            .build()
            .create(Api::class.java)
    }
}