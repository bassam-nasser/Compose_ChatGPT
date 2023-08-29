package com.megatrust.composechatgpt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megatrust.composechatgpt.data.Message
import com.megatrust.composechatgpt.data.remote.CompletionResponse
import com.megatrust.composechatgpt.repositories.QuestionsRepository
import com.megatrust.composechatgpt.utills.Resource
import com.megatrust.composechatgpt.utills.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionsRepository) :
    ViewModel() {

    //
    private val _completionLiveData: MutableLiveData<Resource<Message>> =
        MutableLiveData(Resource.Unspecified())

    val completionLiveData: LiveData<Resource<Message>> = _completionLiveData

    suspend fun getApiAnswer(question: String) {
        viewModelScope.launch {
            _completionLiveData.postValue(Resource.Loading())
            try {
                handelApiResponse(repository.getApiQuestion(question))
            } catch (e: Exception) {
                _completionLiveData.postValue(Resource.Error(e.message))
            }
        }
    }

    private fun handelApiResponse(completionResponse: Response<CompletionResponse>) {
        if (completionResponse.isSuccessful) {
            _completionLiveData.postValue(
                Resource.Success(completionResponse.body()?.choices?.firstOrNull()
                    ?.let { Message(it.text, Message.SENT_BY_BOT, getCurrentTime()) })
            )
        } else {
            _completionLiveData.postValue(Resource.Error(completionResponse.errorBody().toString()))
        }
    }
}