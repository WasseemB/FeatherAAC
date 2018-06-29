package com.wasseemb.featheraac.Repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditChildrenResponse


class RedditDataSourceFactory(
    private val redditApiService: RedditApiService) : DataSource.Factory<String, RedditChildrenResponse>() {
  val sourceLiveData = MutableLiveData<PagedKeyRedditDataSource>()

  override fun create(): DataSource<String, RedditChildrenResponse> {
    val source = PagedKeyRedditDataSource(redditApiService)
    sourceLiveData.postValue(source)
    return source
  }
}