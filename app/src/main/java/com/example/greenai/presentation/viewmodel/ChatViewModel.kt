package com.example.greenai.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.domain.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch


class ChatViewModel(

): ViewModel(){

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }
    private val model = GenerativeModel(
        modelName = "gemini-3.5-flash",
        apiKey = "AQ.Ab8RN6KPeuUlCbSX3AalC4p4G8qnO4CK1R2mRE8rDdnG7sKAjw"
    )

    private val chat = model.startChat()
    fun sendMessage(
        question: String,
    ) {
        if (question.isBlank()) return

        viewModelScope.launch {
            try {

                messageList.add(MessageModel(question,"user"))

                messageList.add(MessageModel("Thinking...","bot"))

                val response = chat.sendMessage(question)
                val text = response.text ?: "Empty response"
                messageList.removeAt(messageList.lastIndex)
                messageList.add(MessageModel(text,"bot"))
                
            } catch (e: Exception) {
                messageList.removeAt(messageList.lastIndex)
                messageList.add(MessageModel("Error:"+e.message,"bot"))
            }
        }
    }
}