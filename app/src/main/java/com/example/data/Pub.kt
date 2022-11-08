package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.PubTags

@Entity(tableName = "pub_table")
data class Pub(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "lat")
    val lat: String,
    @ColumnInfo(name = "lon")
    val lon: String){
//TODO prepojit s tags cez foreign key id PubTags

}