package com.wasseemb.featheraac.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.wasseemb.featheraac.Api.RedditPost


/**
 * Created by Wasseem on 13/12/2017.
 */
@Database(
    entities = arrayOf(RedditPost::class),
    version = 1,
    exportSchema = false
)
abstract class RedditDb : RoomDatabase() {
  companion object {
    fun create(context: Context, useInMemory: Boolean): RedditDb {
      val databaseBuilder = if (useInMemory) {
        Room.inMemoryDatabaseBuilder(context, RedditDb::class.java)
      } else {
        Room.databaseBuilder(context, RedditDb::class.java, "reddit.db")
      }
      return databaseBuilder
          .fallbackToDestructiveMigration()
          .build()
    }
  }

  abstract fun posts(): RedditPostDao
}


