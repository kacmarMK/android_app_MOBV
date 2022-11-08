package com.example.data

data class RequestBody (
    var collection: String = "bars",
    var database: String = "mobvapp",
    var datasource: String = "Cluster0"
)