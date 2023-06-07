package com.example.mvvm.respositories

import com.example.mvvm.models.Live
import com.example.mvvm.rest.RetrofitService
import retrofit2.Call

/*
class MainRepository constructor (private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()

}*/

class MainRepository (private val retrofitService: RetrofitService) : RetrofitService {

    override fun getAllLives(): Call<List<Live>> {
        return retrofitService.getAllLives()
    }

}
