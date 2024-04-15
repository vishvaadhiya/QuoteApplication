package com.example.quoteapplication.models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quoteapplication.Language
import com.example.quoteapplication.Pages
import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.Charset

object DataManager {

    var data = emptyArray<Quote>()
    var englishData = emptyArray<Quote>()
    var gujaratiData = emptyArray<Quote>()
    var hindiData = emptyArray<Quote>()
    var currentItem = mutableStateOf(Pages.LISTING)
    var currentQuote: Quote? = null
    val isDataLoaded = mutableStateOf(false)
//    var selectedLanguage = Language.GUJARATI
    var selectedLanguage = mutableStateOf(Language.ENGLISH)

    fun loadAssetFromFile(context: Context) {
        var inputStream: InputStream? = null
        inputStream = when (selectedLanguage.value) {
            Language.ENGLISH -> context.assets.open("quotes.json")
            Language.GUJARATI -> context.assets.open("gujratiquotes.json")
            Language.HINDI -> context.assets.open("hindiquotes.json")
        }

        //val inputSteam = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.defaultCharset())
        val gson = Gson()
//        data = gson.fromJson(json, Array<Quote>::class.java)

        when (selectedLanguage.value) {
            Language.ENGLISH -> {
                englishData = gson.fromJson(json, Array<Quote>::class.java)
            }
            Language.GUJARATI -> {
                gujaratiData = gson.fromJson(json, Array<Quote>::class.java)
            }
            else -> {
                hindiData = gson.fromJson(json, Array<Quote>::class.java)
            }
        }
        isDataLoaded.value = true
    }

    fun switchPages(quote: Quote?) {
        if (currentItem.value == Pages.LISTING) {
            currentQuote = quote
            currentItem.value = Pages.DETAIL
        } else {
            currentItem.value = Pages.LISTING
        }
    }
}