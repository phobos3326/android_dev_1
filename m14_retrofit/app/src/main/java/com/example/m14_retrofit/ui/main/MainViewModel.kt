package com.example.m14_retrofit.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import com.example.m14_retrofit.ui.main.network.data.Test


import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

var str=""
    init {
      //  getUserPhoto()

    }

    private var _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status



}