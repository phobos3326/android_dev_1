package com.example.m17_recyclerview.ui.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.m17_recyclerview.data.ModelPhotoRepository
import com.example.m17_recyclerview.entity.ModelPhotos
import javax.inject.Inject

class PhotoPagingSource @Inject constructor(private val repository: ModelPhotoRepository) :
    PagingSource<Int, ModelPhotos.Photo>() {

    override fun getRefreshKey(state: PagingState<Int, ModelPhotos.Photo>): Int? =1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelPhotos.Photo> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getPagedPhotos(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.photos,
                    prevKey = null,
                    nextKey = if (it.photos.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )

    }
}