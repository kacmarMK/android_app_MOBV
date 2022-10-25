package com.example.model

import com.google.gson.Gson
import java.io.InputStream


object JsonData {
    lateinit var enterprises: MutableList<Pub>

    fun loadEnterprises(inputStream: InputStream) {
        enterprises = Gson().fromJson(inputStream.bufferedReader(), Enterprises::class.java)?.elements ?: mutableListOf()
    }

}