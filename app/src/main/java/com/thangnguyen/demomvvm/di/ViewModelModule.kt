package com.thangnguyen.demomvvm.di

import com.thangnguyen.demomvvm.viewmodel.ListRepoViewModel
import com.thangnguyen.demomvvm.viewmodel.DetailsRepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // ui
    viewModel {
        ListRepoViewModel(get())
    }
    viewModel { (userName: String, repoName: String) ->
        DetailsRepoViewModel(userName, repoName, get())
    }

}
