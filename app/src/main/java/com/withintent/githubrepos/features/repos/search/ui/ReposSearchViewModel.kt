package com.withintent.githubrepos.features.repos.search.ui

import androidx.lifecycle.ViewModel
import com.withintent.githubrepos.coroutines.InputFlow
import com.withintent.githubrepos.coroutines.clear
import com.withintent.githubrepos.coroutines.launch
import com.withintent.githubrepos.coroutines.mutableStateFlow
import com.withintent.githubrepos.features.repos.search.SearchInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ReposSearchViewModel @Inject constructor() : ViewModel() {

    internal var searchedTextInput = InputFlow(SearchInputValidator)

    private val _searchedKeyword = mutableStateFlow<String>()
    val searchedKeyword: StateFlow<String?> = _searchedKeyword

    fun onSearchTextChange(newValue: String) {
        searchedTextInput.onValueChanged(newValue)
    }

    fun onSearchRequest() {
        if (searchedTextInput.isValid()) {
            launch { _searchedKeyword.emit(searchedTextInput.actualValue()) }
        }
    }

    fun resetKeyword() = _searchedKeyword.clear()

}