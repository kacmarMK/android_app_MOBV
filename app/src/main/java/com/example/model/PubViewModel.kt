package com.example.model

import androidx.lifecycle.ViewModel

class PubViewModel : ViewModel(){
    private var _pubName = "test"

    val pubName: String
        get() = _pubName

}