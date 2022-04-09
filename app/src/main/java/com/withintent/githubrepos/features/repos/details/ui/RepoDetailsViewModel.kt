package com.withintent.githubrepos.features.repos.details.ui

import androidx.lifecycle.ViewModel
import com.withintent.githubrepos.coroutines.ResourceFlowMediator
import com.withintent.githubrepos.coroutines.emitValue
import com.withintent.githubrepos.coroutines.mutableStateFlow
import com.withintent.githubrepos.features.repos.ReposRepository
import com.withintent.githubrepos.features.repos.details.model.RepoDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: ReposRepository
) : ViewModel() {

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    private val _details = mutableStateFlow<RepoDetailsModel>()
    val details: StateFlow<RepoDetailsModel?> = _details

    private val _errorMessage = mutableStateFlow<String>()
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadDetails(owner: String, repo: String) {
        ResourceFlowMediator(
            source = repository.getDetails(owner, repo),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = { emitValue(_details, it) },
            onError = { emitValue(_errorMessage, it) }
        ).begin()
    }
}