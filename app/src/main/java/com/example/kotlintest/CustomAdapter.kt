package com.example.kotlintest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.kotlintest.Model.City
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/*
class CustomAdapter(private val mContext: Context, private val listCities:List<City>): ArrayAdapter<City>(mContext, 0, listCities){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.adapter_layout, parent, false)
        val city = listCities[position]
        val imageView = layout.findViewById<ImageView>(R.id.cityPicture)
        Picasso.with(context).load(city.url).fit().into(imageView)
        layout.cityName.text = city.name
        return layout
    }
}
*/

class CustomAdapter(private val context: Context,
                    private val citiesList : List<City>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val finalView = inflater.inflate(R.layout.adapter_layout, parent, false)

        val cityName = finalView.findViewById<TextView>(R.id.cityName)
        val cityButton = finalView.findViewById<Button>(R.id.cityButton)
        val countryName = finalView.findViewById<TextView>(R.id.countryName)
        val cityPicture = finalView.findViewById<ImageView>(R.id.cityPicture)
        val cityProgressBar = finalView.findViewById<ProgressBar>(R.id.progressBar)

        cityName.text = citiesList[position].name
        countryName.text = citiesList[position].country

        // Show progress bar
        cityProgressBar.visibility = View.VISIBLE
        // Hide progress bar on successful load
        Picasso
            .with(context)
            .load(citiesList[position].url)
            .fit()
            .into(cityPicture, object : Callback {
                override fun onSuccess() {
                    cityProgressBar?.visibility = View.GONE
                }
                override fun onError() {}
            })

        cityButton.setOnClickListener {
            println("CLICKED!")

            val intent = Intent(context, CityMap::class.java)
            intent.putExtra("city", citiesList[position].name)
            context.startActivity(intent)
        }

        return finalView
    }

    override fun getItem(position: Int): Any {
        return citiesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return citiesList.size
    }
}
