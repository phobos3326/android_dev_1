package com.example.m14_retrofit.ui.main


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    //private val context = application.applicationContext
    private val _state = MutableStateFlow<State>(State.ColdStart)
    val state = _state.asStateFlow()

    init {

        start()

       // isNetworkAvailable(application)
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


/*    private fun isNetworkAvailable(): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
            _state.value=State.ColdStart
            return true
        }

        return false
    }*/


    private var isConnect = true
    fun isNetworkAvailable(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            _state.value = State.ColdStart
            Log.d("TAG", "$isConnect, ${_state.value}")
            isConnect = true


        } else {

            _state.value = State.Error
            Log.d("TAG", "$isConnect, ${_state.value}")
            isConnect = false
        }
    }


    fun start() {
        isNetworkAvailable(getApplication<Application>().applicationContext)
        if (isConnect) {

            viewModelScope.launch {


                try {
                    _state.value = State.Wait
                    val response = RetrofitInstance.searchUserApi.getUser()
                    val user = response.body()
                    val status = response.code()
                    Log.d("TAG", "$status, ${  _state.value}")
                    _user.value = user?.results?.first()?.name?.first
                    _userLastName.value = user?.results?.first()?.name?.last
                    _userCode.value = status
                    _userImg.value = user?.results?.first()?.picture?.large
                    _state.value = State.Completed
                    Log.d("TAG", "$status, ${  _state.value}")
                    //_state.value = State.Wait
                } catch (e: Exception) {

                    _state.value = State.Error

                }
            }


        } else {
            _state.value = State.Error
        }


    }


}