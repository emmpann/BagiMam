package com.github.emmpann.bagimam.di


import com.github.emmpann.bagimam.MainViewModel
import com.github.emmpann.bagimam.login.LoginViewModel
import com.github.emmpann.bagimam.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }
}