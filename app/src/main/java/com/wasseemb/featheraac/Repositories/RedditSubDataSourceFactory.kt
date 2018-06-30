package com.wasseemb.featheraac.Repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditChildrenResponse


class RedditSubDataSourceFactory(
    private val redditApiService: RedditApiService,
    private val subreddit: String) : DataSource.Factory<String, RedditChildrenResponse>() {
  val sourceSubLiveData = MutableLiveData<PagedKeyRedditSubDataSource>()

  override fun create(): DataSource<String, RedditChildrenResponse> {
    val source = PagedKeyRedditSubDataSource(redditApiService, subreddit)
    sourceSubLiveData.postValue(source)
    return source
  }
}