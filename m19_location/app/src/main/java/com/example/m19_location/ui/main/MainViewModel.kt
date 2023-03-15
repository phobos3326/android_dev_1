package com.example.m19_location.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m19_location.data.ModelLandmarkDto
import com.example.m19_location.data.ModelLandmarkRepository
import com.example.m19_location.domain.UseCase
import com.example.m19_location.entity.ModelLandmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val rep =ModelLandmarkRepository()
    val landmarkData =UseCase(rep)

    private var _landmark = MutableStateFlow<List<ModelLandmark.modelItem>>(emptyList())
    var landmark =_landmark.asStateFlow()

    init {
        loadLandmark()
    }

    private fun loadLandmark(){
        viewModelScope.launch(Dispatchers.IO) {

            kotlin.runCatching {


            }.fold(
                onSuccess = {
                    val qq=landmarkData.getLandmarkUseCase()
                    _landmark.value= qq
                            Log.d("TAG", "$it")},
                onFailure = { Log.d("TAG", it.message?: "not load")}
            )
        }
    }

}