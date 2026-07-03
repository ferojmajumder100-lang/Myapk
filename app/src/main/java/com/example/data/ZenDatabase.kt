package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class, Note::class], version = 1, exportSchema = false)
abstract class ZenDatabase : RoomDatabase() {
    abstract fun zenDao(): ZenDao

    companion object {
        @Volatile
        private var INSTANCE: ZenDatabase? = null

        fun getDatabase(context: Context): ZenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZenDatabase::class.java,
                    "zen_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
