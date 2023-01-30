package com.example.m16_architecture.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.m16_architecture.data.UsefulActivityRepository
import com.example.m16_architecture.domain.GetUsefulActivityUseCase

import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val useCase: GetUsefulActivityUseCase,

) : ViewModel() {


    init {
        start()
    }

    private fun isNetworkAvialable(applicationContext: Context) {
        viewModelScope.launch {
            val cm =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }

    fun start() {
       // isNetworkAvialable(getApplication<Application>().applicationContext)
        viewModelScope.launch {
            val data = useCase
            val aa = data.execute().activity
            Log.d("TAG", "$aa")
        }

    }
}