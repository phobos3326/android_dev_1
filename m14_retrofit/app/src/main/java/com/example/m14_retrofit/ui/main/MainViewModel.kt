package com.example.m14_retrofit.ui.main


import android.app.Application
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.transition.Transition
import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import com.example.m14_retrofit.ui.main.network.data.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {



    var str = ""

    init {
        //  getUserPhoto()
        // start()
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


    fun start() {

        RetrofitInstance.searchUserApi.getUser().enqueue(object : Callback<Test> {
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                val user = response.body() ?: return
                val status = response.code()
                val  isStatus=response.isSuccessful
                //binding.message.text = user.toString()
                Log.d("TAG", "$isStatus")
                _user.value = user.results.first().name.first
                _userCode.value = status
                _userImg.value=user.results.first().picture.large







            }


            /* .forEach {
             binding.gender.text = it.gender
             binding.name.text = buildString {
                 append("${it.name.first} ")
                 append("${it.name.last} ")
                 append("${it.name.title} ")
             }
             Glide
                 .with(this@MainFragment)
                 .load(it.picture.large)
                 .into(binding.imageView)

         }*/
            override fun onFailure(call: Call<Test>, t: Throwable) {

            }

        })
    }






}