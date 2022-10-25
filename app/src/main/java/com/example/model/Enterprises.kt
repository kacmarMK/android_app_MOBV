package com.example.model

data class Enterprises(val elements: MutableList<Pub>? = null) {

    override fun toString(): String {
        return "Enterprises(elements=$elements)"
    }
}