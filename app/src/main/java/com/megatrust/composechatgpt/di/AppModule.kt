package com.megatrust.composechatgpt.di

import com.megatrust.composechatgpt.networking.ChatGPTApiService
import com.megatrust.composechatgpt.repositories.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(
        chatGptApiService: ChatGPTApiService
    ) = QuestionsRepository(chatGptApiService)


}
