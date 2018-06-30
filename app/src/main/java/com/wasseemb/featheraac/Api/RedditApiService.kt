package com.wasseemb.featheraac.Api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

  companion object {
    private const val API_URL = "https://www.reddit.com"
    fun create(): RedditApiService {
      val retrofit = Retrofit.Builder()
          .addConverterFactory(
              GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

          .baseUrl(API_URL)

          // .client(httpClient.build())
          .build()
      return retrofit.create(RedditApiService::class.java)
    }
  }
}

