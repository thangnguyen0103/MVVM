package com.thangnguyen.demomvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thangnguyen.demomvvm.data.Resource
import com.thangnguyen.demomvvm.data.repository.RepoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailsRepoViewModel(
    private val userName: String,
    private val repoName: String,
    private val repository: RepoRepository
) : ViewModel() {

    private val loadEvent = MutableSharedFlow<Unit>()

    private val repo = loadEvent
        .flatMapLatest {
            repository.getRepoDetail(userName, repoName)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading)

    val isLoading = repo.map { it.isLoading }
    val isFail = repo.map { it.isFail }
    val data = repo.map { it.valueOrNull }

    init {
        submit()
    }

    fun retry() {
        submit()
    }

    fun submit() {
        viewModelScope.launch {
            loadEvent.emit(Unit)
        }
    }
}