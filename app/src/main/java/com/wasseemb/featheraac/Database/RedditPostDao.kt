package com.wasseemb.featheraac.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.wasseemb.featheraac.Api.RedditPost


/**
 * Created by Wasseem on 13/12/2017.
 */
@Dao
interface RedditPostDao {
  @Insert
  fun insert(redditNewsDataResponse: List<RedditPost>)


  @Query("SELECT * FROM posts")
  fun getAllPosts(): List<RedditPost>
}