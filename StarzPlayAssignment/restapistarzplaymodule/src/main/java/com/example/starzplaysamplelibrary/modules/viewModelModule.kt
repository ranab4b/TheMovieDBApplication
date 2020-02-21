package com.example.starzplaysamplelibrary.modules



import com.example.starzplaysamplelibrary.MultiSearchViewModel
import com.example.starzplaysamplelibrary.core.BaseViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel {
        MultiSearchViewModel()
    }
    viewModel {
        BaseViewModel()
    }

}