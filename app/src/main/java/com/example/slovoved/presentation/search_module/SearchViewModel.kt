package com.example.slovoved.presentation.search_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


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

    fun changeDefinitionIndex(index: Int) {
        if (index < _searchState.value.definitionList.size && index>=0) {
            _searchState.update { currentState ->
                currentState.copy(definitionIndex = index)
            }
        }
    }
    fun changeSearchingWord(word: String) {
        _searchState.update { currentState ->
            currentState.copy(word = word, definitionIndex = 0)
        }
        search()
    }

    private val _searchState = MutableStateFlow(Search())
    val searchState = _searchState.asStateFlow()


    private fun search() {
        _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Loading)}
        viewModelScope.launch(Dispatchers.IO)
        {
            try {
                var page = 1
                val url  = "https://gramota.ru/poisk?query=${_searchState.value.word}&mode=slovari&simple=0&dicts[]=50&dicts[]=52&dicts[]=42"
                var document: Document = Jsoup.connect(url).get()
                val countOfPages = document.select(".pagination > li").size
                val newDefinitionList = mutableListOf<String>()

                do {
                    for(ix in document.getElementsByClass("description highlightable")) { newDefinitionList.add(ix.text())}
                    page++
                    if(page<=countOfPages) { document = Jsoup.connect(url).data("page","$page").get() }
                }while (page <= countOfPages)

                _searchState.update { currentState ->
                    currentState.copy(searchStatus = SearchStatus.Success, definitionList = newDefinitionList)
                //currentState.copy(definitionList = newDefinitionList)
                }
            }
            catch (e: HttpStatusException ) {
                _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Error,definitionList = mutableListOf())}
            }
            catch (e : Exception) {
                _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Error,definitionList = mutableListOf())}
            }
            finally {
                if (_searchState.value.word == "") {
                    _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Waiting, definitionList = mutableListOf())}
                }
                else {
                    if(_searchState.value.definitionList.size == 0) {
                        _searchState.update { currentState -> currentState.copy(searchStatus = SearchStatus.Error,definitionList = mutableListOf())}
                    }
                }
            }
        }
    }
}
