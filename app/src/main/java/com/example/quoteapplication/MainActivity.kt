package com.example.quoteapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quoteapplication.models.DataManager
import com.example.quoteapplication.screens.QuoteDetails
import com.example.quoteapplication.screens.QuoteListScreen
import com.example.quoteapplication.ui.theme.QuoteApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            DataManager.selectedLanguage.value = Language.ENGLISH
            DataManager.loadAssetFromFile(applicationContext)
        }
        setContent {
            QuoteApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {

    val dataManager = remember { DataManager }

    if (dataManager.isDataLoaded.value) {
        if (dataManager.currentItem.value == Pages.LISTING) {
            val dataToShow = if (dataManager.selectedLanguage.value == Language.ENGLISH) {
                dataManager.englishData
            } else if (dataManager.selectedLanguage.value == Language.GUJARATI){
                dataManager.gujaratiData
            }else{
                dataManager.hindiData
            }
            QuoteListScreen(data = dataToShow) {
                dataManager.switchPages(it)
            }

        } else {
            dataManager.currentQuote?.let {
                QuoteDetails(quote = it)
            }
        }

    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
            Text(text = "Loading...", style = MaterialTheme.typography.titleMedium)
        }
    }
}

enum class Pages {
    LISTING,
    DETAIL
}

enum class Language {
    ENGLISH,
    GUJARATI,
    HINDI,
}




















