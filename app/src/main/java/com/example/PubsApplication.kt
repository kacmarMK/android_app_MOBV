package com.example

import android.app.Application
import com.example.data.PubRoomDatabase
import com.example.repository.PubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PubsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { PubRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PubRepository(database.pubDao()) }
}