package com.example.kotlintest

//import com.google.android.gms.location.FusedLocationProviderClient
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CityMap : AppCompatActivity(), OnMapReadyCallback {
    //private lateinit var locationClient : FusedLocationProviderClient
    private lateinit var cityName : String
    private lateinit var lastlocation : Location
    private lateinit var cityLocation :LatLng
    companion object{
        private const val   LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val city = intent.getSerializableExtra("city") as String
        cityName = city
        print("----------------------- ${cityName} -----------------------")
        //locationClient = LocationServices.getFusedLocationProviderClient(this)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setUpMap()
        mMap.addMarker(MarkerOptions().position(cityLocation))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cityLocation))
        mMap.uiSettings.isZoomControlsEnabled = true
    }
    private fun setUpMap(){
        //configuracion para ubicacion de usuario
        /*if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        locationClient.lastLocation.addOnSuccessListener (this){location ->
            if(location !=null){
                    lastlocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,13f))
            }
        }*/
        val Barcelona = LatLng(41.3828939, 2.1774322)
        val Londres = LatLng(51.5073219, -0.1276474)
        val Berlin = LatLng(52.5170365,13.3888599)
        val Madrid = LatLng(40.4167047,-3.7035825)
        val Roma = LatLng(41.894802,12.4853384)
        if(cityName == "Barcelona"){
            cityLocation = Barcelona
        }
        else if(cityName =="Londres"){
            cityLocation = Londres
        }
        else if (cityName =="Berlin"){
            cityLocation = Berlin
        }
        else if(cityName == "Madrid"){
            cityLocation = Madrid
        }
        else if (cityName == "Rome"){
            cityLocation = Roma
        }
        else{
            cityLocation = LatLng(-34.6092997,-58.392555)
        }
    }
}
