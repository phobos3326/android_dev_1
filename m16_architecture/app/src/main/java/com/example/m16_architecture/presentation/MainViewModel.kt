package com.example.m16_architecture.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m16_architecture.domain.GetUsefulActivityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val useCase: GetUsefulActivityUseCase,

    ) : ViewModel() {

    val activityTitle = ""

    private var _state = MutableStateFlow<State>(State(activity = activityTitle))
    var state = _state.asStateFlow()

    /*private fun isNetworkAvialable(applicationContext: Context) {
        viewModelScope.launch {
            val cm =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }*/

    fun start() {
        viewModelScope.launch {
            val data = useCase
            val activityTitle = data.execute().activity
            Log.d("TAG", "$activityTitle")
            _state.value = State(activityTitle)
        }

    }
}