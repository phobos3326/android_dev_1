package com.example.m17_recyclerview.di

import com.example.m17_recyclerview.ui.main.MainViewModelFactory
import dagger.Component

@Component
interface AppComponent {

    fun mainViwModelFactory():MainViewModelFactory
}