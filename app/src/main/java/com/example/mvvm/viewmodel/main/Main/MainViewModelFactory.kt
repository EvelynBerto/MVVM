package com.example.mvvm.viewmodel.main.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.respositories.MainRepository
import com.example.mvvm.viewmodel.main.MainViewModel

//Essa classe de factory fornece uma maneira de criar instâncias MainViewModel com a dependência
//necessária MainRepository usando o ViewModelProvider.
class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

    //O create método é o método principal da classe fábrica(factory). Leva um modelClass parâmetro que representa
    // a classe do ViewModel que precisa ser criada. Ele retorna uma instância do ViewModel verificando
    // se modelClass é atribuível da MainViewModel classe. Se for, ele cria uma nova instância de MainViewModel passando
    // a MainRepository dependência para seu construtor. A instância é então convertida para o tipo genérico T e retornada.
    //Cria o ViewModel de acordo com a classe que passarmos na modelClass
    override fun <T: ViewModel> create(modelClass : Class<T>) : T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
    } else {
        throw IllegalStateException("ViewModel Not Found")
        }
    }
}