package com.megatrust.composechatgpt.repositories

import com.megatrust.composechatgpt.data.remote.CompletionRequest
import com.megatrust.composechatgpt.data.remote.CompletionResponse
import com.megatrust.composechatgpt.networking.ChatGPTApiService
import retrofit2.Response
import javax.inject.Inject

class QuestionsRepository @Inject constructor(
    private val chatGptApiService: ChatGPTApiService
) {

    suspend fun getApiQuestion(question: String): Response<CompletionResponse> {
        val completionRequest = CompletionRequest(
            maxTokens = 4000,
            model = "text-davinci-003",
            prompt = "$question",
            temperature = 0
        )
        return chatGptApiService.getCompletion(completionRequest)
    }




}