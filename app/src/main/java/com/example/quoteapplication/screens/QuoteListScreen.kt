package com.example.quoteapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quoteapplication.Language
import com.example.quoteapplication.models.DataManager
import com.example.quoteapplication.models.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuoteListScreen(data: Array<Quote>, onClick: (quote: Quote) -> Unit) {
    val dataManager = remember { DataManager }
    Column {
        Text(
            text = "Quotes App",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Monospace
        )

        val context = LocalContext.current
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
//                    delay(2000)
                    dataManager.selectedLanguage.value = Language.ENGLISH
                    dataManager.loadAssetFromFile(context)
                }
            }, modifier = Modifier.padding(10.dp)) {
                Text(text = "English")
            }
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(1000)
                    dataManager.selectedLanguage.value = Language.GUJARATI
                    dataManager.loadAssetFromFile(context)
                }
            }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Gujarati")
            }
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(1000)
                    dataManager.selectedLanguage.value = Language.HINDI
                    dataManager.loadAssetFromFile(context)
                }
            }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Hindi")
            }
        }
        QuoteList(data = data, onClick)
    }
}