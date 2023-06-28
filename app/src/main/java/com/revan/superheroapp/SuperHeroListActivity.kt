package com.revan.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.revan.superheroapp.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding // La vida es mas bonita
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperHeroAdapter()
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroes(query)

            if (myResponse.isSuccessful) {
                Log.i("NameSearch", "Funciona")
                val response: SuperHeroDataResponse? = myResponse.body()

                if (response != null) {
                    Log.i("NameSearch", response.toString())

                    runOnUiThread {
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible = false
                    }
                }



            } else {
                Log.i("NameSearch", "No Funciona")

            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}