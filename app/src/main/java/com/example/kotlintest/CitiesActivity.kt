package com.example.kotlintest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest.Model.City
import com.example.kotlintest.Networking.Api
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_cities.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val intent = Intent(this, HomeActivity::class.java)     //MainActivity
        homeButton.setOnClickListener {
            startActivity(intent)
        }

        var BASE_URL="https://mytinerary-back.herokuapp.com/"

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        fun showCities(allCities: List<City>) {
            val adaptador = CustomAdapter(this, allCities)
            citiesList.adapter = adaptador

            /*
            citiesList.setOnItemClickListener { parent, view, position, id ->
                print("CLICKED!")

                val intent = Intent(this, CityMap::class.java)
                intent.putExtra("city", allCities[position])
                startActivity(intent)
            }
            */
        }

        val service = retrofit.create<Api>(Api::class.java)
        service.getCities().enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>?, response: Response<List<City>>?) {
                var resultado = response?.body()
                println("Resultado:")
                println(resultado)
                var allCities = arrayListOf<City>()
                for (city in resultado.orEmpty()) {
                    val cityToAdd = City(city._id, city.country, city.name, city.url)
                    allCities.add(cityToAdd)
                }
                showCities(allCities.toList())
            }
            override fun onFailure(call: Call<List<City>>?, t: Throwable?) {
                t?.printStackTrace()  }     }
        )


    }
}
