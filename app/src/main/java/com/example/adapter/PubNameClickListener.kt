package com.example.adapter

import android.view.View
import com.example.model.Pub

interface PubNameClickListener {
    fun onPubNameClick(view: View, pub: Pub)
}