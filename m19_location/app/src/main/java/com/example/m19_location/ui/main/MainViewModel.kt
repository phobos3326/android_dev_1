package com.example.m19_location.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.m19_location.data.ModelLandmarkRepository
import com.example.m19_location.domain.UseCase
import com.example.m19_location.entity.ModelLandmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val landmarkData: UseCase): ViewModel() {



    private var _landmark = MutableStateFlow<List<ModelLandmark.Feature>>(emptyList())
    var landmark = _landmark.asStateFlow()

    init {
        loadLandmark()
    }

    private fun loadLandmark() {
        viewModelScope.launch(Dispatchers.IO) {

            kotlin.runCatching {
                landmarkData.getLandmarkUseCase().features

            }.fold(
                onSuccess = {
                        _landmark.value = it

                    Log.d("TAG", "$it")
                },
                onFailure = { Log.d("TAG", it.message ?: "not load") }
            )
        }
    }

}