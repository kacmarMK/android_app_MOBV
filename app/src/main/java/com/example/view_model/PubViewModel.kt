package com.example.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.data.Pub
import com.example.data.RequestBody
import com.example.network.PubsApi
import com.example.repository.PubRepository
import kotlinx.coroutines.launch

class PubViewModel(private val repository: PubRepository) : ViewModel(){

    val allPubs: LiveData<List<Pub>> = repository.allPubs.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(pub: Pub) = viewModelScope.launch {
        repository.insert(pub)
    }
    private fun getPubs(){
        viewModelScope.launch {
            val listResult = PubsApi.retrofitService.getAllPubs(RequestBody())
            Log.d("gtfo", listResult)
        }
    }
}

class PubViewModelFactory(private val repository: PubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}