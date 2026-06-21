package com.example.greenai.ui.screen

import android.content.res.Configuration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenai.R
import com.example.greenai.domain.model.MessageModel
import com.example.greenai.presentation.viewmodel.ChatViewModel
import com.example.greenai.ui.component.Button
import com.example.greenai.ui.component.MyTopAppBar
import com.example.greenai.ui.component.TextFiled
import com.example.greenai.ui.theme.GreenAIShapes
import com.greenai.ui.theme.GreenAITheme


@Composable
fun ChatbotScreen(
    viewModel: ChatViewModel = viewModel(),
    onBackClick: () -> Unit = {}
){
    var text by remember { mutableStateOf("") }
    ChatbotScreenContent(
        text = text,
        messageList = viewModel.messageList,
        onSendClick = {
            viewModel.sendMessage(text)
            text = "" },
        onTextChange ={
            text = it
        },
        onBackClick = onBackClick
    )

}


@Composable
fun ChatbotScreenContent(
    text: String,
    messageList: List<MessageModel>,
    onBackClick: () -> Unit = {},
    onSendClick: () -> Unit = {},
    onTextChange: (String) -> Unit,
){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar("Chatbot", painterResource(R.drawable.outline_arrow_back_24), onBackClick = onBackClick)}
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MessageList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f)
                    ),
                messageList = messageList

            )

            ChatInput(
                text = text,
                onTextChange =onTextChange,
                onSendClick = onSendClick
            )

        }
    }
}

@Composable
fun MessageList(
    modifier: Modifier = Modifier ,
    messageList: List<MessageModel>,
){
    if (messageList.isEmpty()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text("Ask GreenAI", style = MaterialTheme.typography.bodyLarge )
        }
    }
    else{
        LazyColumn(
            modifier = modifier,
        ) {
            items(messageList.size){
                    idx->
                MessageRow(
                    messageModel = messageList[idx]
                )
            }
        }
    }



}
@Composable
fun MessageRow(messageModel: MessageModel) {
    val isBot = messageModel.role == "bot"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isBot) Arrangement.Start else Arrangement.End
    ) {

        val backgroundColor = if (isBot) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = messageModel.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isBot) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }


                Spacer(modifier = Modifier.height(4.dp))

//                Text(
//                    text = currentTime,
//                    style = MaterialTheme.typography.labelSmall,
//                    color = if (isBot) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurfaceVariant ,
//                    modifier = Modifier.align(Alignment.End)
//                )
            }
        }
    }
}
@Composable
fun ChatInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit = {}
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, MaterialTheme.colorScheme.primary.copy(alpha = .2f))
            ))
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .imePadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        TextFiled(
            modifier = Modifier.weight(1f),
            text = text,
            hint = "chat with GreenAI",
            onTextChange = onTextChange,
            shape = MaterialTheme.shapes.extraLarge,
            maxLine = 5,
        )
        Button(
            onClick = onSendClick,
            icon = painterResource(R.drawable.send),
            shape = GreenAIShapes.extraLarge,
            enabled = !text.isBlank()
        )
    }
}

@Preview()
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChatbotScreenPreview() {
    GreenAITheme {
        ChatbotScreenContent(
            text = "df",
            messageList = listOf(

            ),
            onTextChange = {

            }

        )
    }
}