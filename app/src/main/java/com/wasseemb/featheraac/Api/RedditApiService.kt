package com.wasseemb.featheraac.Api

import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Wasseem on 01/08/2017.
 */
interface RedditApiService {

  @GET("/{mode}/.json")
  fun getNewsSort(
      @Path("mode") mode: String, @Query("limit") limit: String?, @Query(
          "after") after: String?, @Query("sort") sort: String?, @Query(
          "t") time: String?)
      : Observable<RedditNewsResponse>

  @GET("r/{subreddit}/{mode}/.json")
  fun openSub(@Path("subreddit") subreddit: String, @Path("mode") mode: String,
      @Query("after") after: String?, @Query("sort") sort: String?, @Query(
          "t") time: String?): Observable<RedditNewsResponse>

  @GET("/{mode}/.json")
  fun getNewsSorst(
      @Path("mode") mode: String, @Query("limit") limit: String?, @Query(
          "after") after: String?, @Query("sort") sort: String?, @Query(
          "t") time: String?)
      : LiveData<RedditNewsResponse>


}

