package com.megatrust.composechatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}