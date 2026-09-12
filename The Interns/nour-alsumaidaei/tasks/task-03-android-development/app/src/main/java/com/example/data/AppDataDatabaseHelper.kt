package com.example.data

import android.content.Context

object AppDataDatabaseHelper {
    @Volatile
    private var repository: AppDataRepository? = null

    fun getRepository(context: Context): AppDataRepository {
        return repository ?: synchronized(this) {
            val db = AppDatabase.getDatabase(context)
            val repo = AppDataRepository(db.appDataDao())
            repository = repo
            repo
        }
    }
}
