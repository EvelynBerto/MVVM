package com.example.mvvm.rest

import com.example.mvvm.models.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("lista-lives.json")
    fun getAllLives(): Call<List<Live>>

    //Companion object - Cria membros estáticos, permitindo acesso sem a necessidade de instância da classe
    companion object {

        /* By lazy = permite a inicialização tardia de propriedades, ou seja, adiar a inicialização
        de uma propriedade até o momento em que ela é realmente necessária. Isso é útil em situações
        em que a inicialização de uma propriedade pode ser custosa em termos de recursos computacionais,
        tempo ou dependências externas. Ao usar by lazy, você evita a inicialização desnecessária da
        propriedade e só a realiza quando ela é acessada pela primeira vez. Nas chamadas subsequentes,
        o valor já inicializado é retornado, evitando repetidas reinicializações. */

        private val retrofitServices: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com/api.ocanha.com/lista-lives.json")
                .addConverterFactory(GsonConverterFactory.create()).build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance(): RetrofitService {
            return retrofitServices
        }
    }
}