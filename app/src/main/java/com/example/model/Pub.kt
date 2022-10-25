package com.example.model

data class Pub(val id: Long, val lat: String, val lon: String,
                val tags: PubTags){

    override fun toString(): String {
        return "Pub(id=$id, lat=$lat, lon=$lon, tags=$tags)"
    }
}