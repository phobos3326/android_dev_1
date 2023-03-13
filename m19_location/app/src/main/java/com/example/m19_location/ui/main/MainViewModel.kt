package com.example.m19_location.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m19_location.domain.UseCase
import com.example.m19_location.entity.ModelLandmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val landmarkData: UseCase) : ViewModel() {
    private val _landmark = MutableStateFlow<List<ModelLandmark.ModelLandmarkItem>>(emptyList())
    val landmark =_landmark.asStateFlow()

    init {
        loadLandmark()
    }

    private fun loadLandmark(){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                landmarkData.getLandmarkUseCase().landmark
            }.fold(
                onSuccess = {_landmark.value=it
                            Log.d("TAG", "$it")},
                onFailure = { Log.d("TAG", it.message?: "not load")}
            )
        }
    }

}