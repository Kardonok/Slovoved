package com.example.slovoved.presentation.search_module

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException


sealed interface SearchStatus
{
    data object Success : SearchStatus
    data object Waiting : SearchStatus
    data object Error : SearchStatus
    data object Loading : SearchStatus
}

data class Search(
    val word: String = "",
    val definitionIndex: Int = 0,
    val definitionList: MutableList<String> = mutableListOf(),
    val searchStatus: SearchStatus = SearchStatus.Waiting
)

class SearchViewModel : ViewModel() {

    private val _searchState = MutableStateFlow(Search())
    val searchState = _searchState.asStateFlow()

    private suspend fun getDocument(word:String, page:Int = 0): Document {
        if (word.isEmpty()) { throw IOException("The word is missing") }

        val url = if(page!=0) "https://gramota.ru/poisk?query=${word}&mode=slovari&simple=0&page=${page}" else "https://gramota.ru/poisk?query=${word}&mode=slovari"

        var attempts = 0
        while( attempts < 3) {
            try {
                //может также выбросить исключение IOException если url будет неправельным
                //но это исключение обрабатывается в search
                return Jsoup.connect(url).get()
            }
            catch(e: HttpStatusException) {
                Log.d("SVM", "HttpStatusException $attempts: ${e.message}")
            }
            finally {
                attempts++
                delay(500)
            }
        }
        throw IOException("Failed to connect to the server after 3 attempts. URL: $url")
    }

    private var searchJob: Job? = null
    private fun searchDefinitionInWeb(word: String) {
        searchJob?.cancel()

        _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Loading) }
        searchJob = viewModelScope.launch(Dispatchers.IO)
        {
            val newDefinitionList = mutableListOf<String>()
            try {
                var page = 1
                var document: Document = getDocument(word)
                val countOfPages = document.select(".pagination > li").size
                do {
                    for (ix in document.getElementsByClass("description highlightable")) {
                        newDefinitionList.add(ix.text())
                    }
                    page++
                    if (page <= countOfPages) {
                        document = getDocument(word,page)
                    }
                } while (page <= countOfPages)
                yield()

            } catch (e: CancellationException) {
                Log.d("SVM", "The search for a definition $word has been stopped")
                return@launch
            } catch (e: IOException) {
                Log.d("SVM", "IOException:${e.message}")
            }

            val newSearchStatus = if (_searchState.value.word.isEmpty()) {
                SearchStatus.Waiting
            } else if (newDefinitionList.isEmpty()) {
                SearchStatus.Error
            } else {
                SearchStatus.Success
            }
            _searchState.update { currentState -> currentState.copy(searchStatus = newSearchStatus, definitionList = newDefinitionList) }

        }
    }

    fun changeDefinitionIndex(index: Int) {
        if (index < _searchState.value.definitionList.size && index>=0) {
            _searchState.update { currentState ->
                currentState.copy(definitionIndex = index)
            }
        }
    }
    fun changeQueryWord(word: String) {
        _searchState.update { currentState ->
            currentState.copy(word = word, definitionIndex = 0)
        }
        searchDefinitionInWeb(word)
    }
}
