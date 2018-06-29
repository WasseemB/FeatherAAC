package com.wasseemb.featheraac.Repositories


import android.arch.paging.PageKeyedDataSource
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PagedKeyRedditDataSource(private val redditApiService: RedditApiService) : PageKeyedDataSource<String, RedditChildrenResponse>() {
  override fun loadInitial(params: LoadInitialParams<String>,
      callback: LoadInitialCallback<String, RedditChildrenResponse>) {

    redditApiService.getNewsSort(mode = "hot", time = null, after = null, limit = null,
        sort = null).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe {
      val data = it
      callback.onResult(data.data.children, data.data.before,
          data.data.after)
    }
  }

  override fun loadAfter(params: LoadParams<String>,
      callback: LoadCallback<String, RedditChildrenResponse>) {
    redditApiService.getNewsSort(mode = "hot", time = null, after = params.key, limit = null,
        sort = null).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe {
      val data = it
      callback.onResult(data.data.children, data.data.after)
    }

  }

  override fun loadBefore(params: LoadParams<String>,
      callback: LoadCallback<String, RedditChildrenResponse>) {
  }
}