package com.example.kotlintest.Model

import java.io.Serializable

data class City(val _id:String, val country: String, val name : String, val url:String):
    Serializable
