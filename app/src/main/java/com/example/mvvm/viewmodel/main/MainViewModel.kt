package com.example.mvvm.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.models.Live
import com.example.mvvm.respositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    //LiveData respeita o lifecycle do android, isso significa que ele não invocará seu retorno de chamada
    //do observador, a menos que a aplicação tenha passado pelo onStart() e não tenha passado pelo onStop()
    //e removerá automaticamente o observador quando o host receber onDestroy() liberando memória
    val listLive = MutableLiveData<List<Live>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllLives() {
        val request = mainRepository.getAllLives()
        //implementação de um objeto anônimo usando o object, seguida da definição da interface Callback
        request.enqueue(object : Callback<List<Live>>{
            //Quando houver uma resposta execute isso
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                listLive.postValue(response.body())
            }
            //Quando houver uma falha execute isso
            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })

    }
}