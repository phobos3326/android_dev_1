package com.example.m17_recyclerview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.m17_recyclerview.entity.ModelPhotos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
class PagingViewModel: ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading =_isLoading.asStateFlow()

    val pagedPhoto: Flow<PagingData<ModelPhotos.Photo>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {PhotoPagingSource()}
    ).flow.cachedIn(viewModelScope)
}*/
