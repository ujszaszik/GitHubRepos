package com.withintent.githubrepos.features.repos.list.ui

import androidx.lifecycle.ViewModel
import com.withintent.extension.isZero
import com.withintent.githubrepos.coroutines.ResourceFlowMediator
import com.withintent.githubrepos.coroutines.emitValue
import com.withintent.githubrepos.coroutines.mutableStateFlow
import com.withintent.githubrepos.features.repos.ReposRepository
import com.withintent.githubrepos.features.repos.list.model.RepoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val repository: ReposRepository
) : ViewModel() {

    private var currentElements = mutableListOf<RepoItem>()
    private var currentPageNumber = 0L
    private var pageSize = 30

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    val isRefreshing = _isLoading.transform<Boolean?, Boolean> { isLoading ->
        true == isLoading && currentPageNumber.isZero()
    }

    private val _itemDetails = mutableStateFlow<RepoItemDetails>()
    val itemDetails: StateFlow<RepoItemDetails?> = _itemDetails

    private val _errorMessage = mutableStateFlow<String>()
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadRepositories(keyword: String) {
        ResourceFlowMediator(
            source = repository.searchByKeyword(keyword, ++currentPageNumber),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = {
                currentElements.addAll(it.items)
                val isLastPage = it.itemsCount <= currentPageNumber * pageSize
                emitValue(_itemDetails, RepoItemDetails(currentElements, it.itemsCount, isLastPage))
            },
            onError = { emitValue(_errorMessage, it) }
        ).begin()
    }

    class RepoItemDetails(
        val items: List<RepoItem>,
        val totalCount: Long,
        val finishedLoading: Boolean
    )
}