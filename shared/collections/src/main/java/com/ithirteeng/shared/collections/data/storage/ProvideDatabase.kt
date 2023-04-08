package com.ithirteeng.shared.collections.data.storage

import android.app.Application
import androidx.room.Room

fun provideDatabase(application: Application): CollectionsDatabase {
    return Room.databaseBuilder(
        application, CollectionsDatabase::class.java,
        "collections_database"
    ).build()
}