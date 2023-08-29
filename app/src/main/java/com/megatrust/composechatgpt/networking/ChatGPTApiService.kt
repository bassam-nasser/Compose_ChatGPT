package com.megatrust.composechatgpt.networking

import com.megatrust.composechatgpt.MyApplication.Companion.CHAT_GPT_API_KEY
import com.megatrust.composechatgpt.data.remote.CompletionRequest
import com.megatrust.composechatgpt.data.remote.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGPTApiService {
    @Headers("Authorization: Bearer $CHAT_GPT_API_KEY")
    @POST("v1/completions")
    suspend fun getCompletion(
        @Body completionRequest: CompletionRequest
    ): Response<CompletionResponse>

}