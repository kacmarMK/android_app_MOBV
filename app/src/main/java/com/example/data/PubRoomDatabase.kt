package com.example.data

import android.content.Context
import android.util.Log
import androidx.compose.ui.text.android.animation.SegmentType
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.network.PubsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Pub::class), version = 1, exportSchema = false)
abstract class PubRoomDatabase : RoomDatabase() {

    abstract fun pubDao(): PubDAO

    private class PubDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.pubDao())
                }
            }
        }

        suspend fun populateDatabase(pubDao: PubDAO) {
            // Delete all content here.
            pubDao.deleteAll()

            // Add sample words.
            /*var pub = Pub("Test pub 1", "45.845", "87.559")
            pubDao.insert(pub)
            pub = Pub("Test pub 2", "85.845", "11.559")
            pubDao.insert(pub)*/

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PubRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PubRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PubRoomDatabase::class.java,
                    "pub_database"
                ).addCallback(PubDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}