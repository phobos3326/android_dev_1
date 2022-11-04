package com.example.m14_retrofit.ui.main


import android.app.Application

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*

import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import com.example.m14_retrofit.ui.main.network.data.UserModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val _state = MutableStateFlow<State>(State.ColdStart)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            start()
        }

        _state.value = State.ColdStart

    }

    private var _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status


    private val _user = MutableLiveData<String>()
    val user = _user

    private val _userCode = MutableLiveData<Int>()
    val userCode = _userCode

    private val _userImg = MutableLiveData<String>()
    val userImg = _userImg

    private val _userLastName = MutableLiveData<String>()
    val userLastName = _userLastName


     fun start() {

            RetrofitInstance.searchUserApi.getUser()
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        val user = response.body() ?: return
                        val status = response.code()
                        Log.d("TAG", status.toString())
                        _user.value = user.results.first().name.first
                        _userLastName.value = user.results.first().name.last
                        _userCode.value = status
                        _userImg.value = user.results.first().picture.large
                        _state.value = State.Completed
                        _state.value = State.Wait
                    }


                    override fun onFailure(call: Call<UserModel>, t: Throwable) {

                    }

                })


    }


}