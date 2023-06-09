package com.example.mvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.adapter.MainAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.respositories.MainRepository
import com.example.mvvm.rest.RetrofitService
import com.example.mvvm.viewmodel.main.Main.MainViewModelFactory
import com.example.mvvm.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter{
        openLink(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        viewModel.listLive.observe(this, Observer { lives ->
            Log.i("Evelyn", "onStart")
            adapter.setLiveList(lives)
        })
        viewModel.errorMessage.observe(this, Observer{ message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        //Toda vez que o usuário voltar pra essa tela, a lista será atualizada
        super.onResume()
        viewModel.getAllLives()
    }

    private fun openLink (link : String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}