package com.example.slovoved.presentation.search_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

data class Search(
    var word: String? = "холод",
    val definitionList: MutableList<String> = mutableListOf()
)

class SearchViewModel : ViewModel() {

    init {
        search()
    }

    private val _searchState = MutableStateFlow<Search>(Search())
    val searchState = _searchState.asStateFlow()


    fun search() {
        viewModelScope.launch(Dispatchers.IO)
        {
            var page:Int = 1
            val url:String  = "https://gramota.ru/poisk?query=${_searchState.value.word ?: ""}&mode=slovari&simple=0&dicts[]=50&dicts[]=52&dicts[]=42"
            var document: Document = Jsoup.connect(url).get()
            val countOfPages = document.select(".pagination > li").size
            val newDefinitionList = mutableListOf<String>()

            do {
                for(ix in document.getElementsByClass("description highlightable")) { newDefinitionList.add(ix.text())}
                page++
                if(page<=countOfPages) { document = Jsoup.connect(url).data("page","$page").get() }
            }while (page <= countOfPages)

            _searchState.update { currentState ->
                currentState.copy(definitionList = newDefinitionList)
            }
        }
    }
}