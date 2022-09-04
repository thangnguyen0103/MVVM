package com.thangnguyen.demomvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.data.repository.RepoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class ListRepoViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    private val _userName = MutableStateFlow("Google")

    val repoFlow: Flow<PagingData<Repo>> = _userName
        .flatMapLatest {
            repoRepository.getRepoList(it)
        }
        .cachedIn(viewModelScope)

    fun setUserName(userName: String) {
        _userName.value = userName
    }

}