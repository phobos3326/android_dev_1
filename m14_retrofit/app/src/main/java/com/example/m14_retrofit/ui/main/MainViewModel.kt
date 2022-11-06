package com.example.m14_retrofit.ui.main


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


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

    private var isConnect = isNetworkAvailable()
    private fun isNetworkAvailable(): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
            return true
        }
        return false
    }

    fun start() {

           if (isConnect) {

               viewModelScope.launch {
                   _state.value=State.Wait

                   try {
                       val response = RetrofitInstance.searchUserApi.getUser()
                       val user = response.body()
                       val status = response.code()
                       Log.d("TAG", status.toString())
                       _user.value = user?.results?.first()?.name?.first
                       _userLastName.value = user?.results?.first()?.name?.last
                       _userCode.value = status
                       _userImg.value = user?.results?.first()?.picture?.large
                       _state.value = State.Completed
                       _state.value = State.Wait
                   } catch (e: Exception) {
                       _state.value=State.Error

                   }
               }


           }else{
               /*while (!isConnect){
                   start()
               }*/

               _state.value=State.Error
           }




        /* RetrofitInstance.searchUserApi.getUser()
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
*/

    }


}