package com.example.greenai.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.domain.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import kotlinx.coroutines.launch


class ChatViewModel(

): ViewModel(){

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }
    private val model = GenerativeModel(
        modelName = "gemini-3.5-flash",
        apiKey = "***",
        systemInstruction = Content(
            parts = listOf(
                TextPart(
                    """
                You are "GreenAI", an intelligent assistant specialized in agriculture, crop disease detection, and agricultural decision support.
                - Always speak as GreenAI, never as Gemini or Google.
                - If asked "who are you?" or "who made you?", respond that you are GreenAI, a smart agricultural assistant.
                - Keep responses concise, clear, and focused on the agricultural context whenever possible.
                """.trimIndent()
                )
            )
        )
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
