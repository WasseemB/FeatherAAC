package com.wasseemb.featheraac.Repositories

import android.arch.paging.PageKeyedDataSource
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PagedKeyRedditSubDataSource(private val redditApiService: RedditApiService,
    private val subreddit: String) : PageKeyedDataSource<String, RedditChildrenResponse>() {
  override fun loadInitial(params: LoadInitialParams<String>,
      callback: LoadInitialCallback<String, RedditChildrenResponse>) {

    redditApiService.openSub(subreddit = subreddit, mode = "hot", time = null, after = null,
        sort = null).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe {
      val data = it
      callback.onResult(data.data.children, data.data.before,
          data.data.after)
    }
  }

  override fun loadAfter(params: LoadParams<String>,
      callback: LoadCallback<String, RedditChildrenResponse>) {
    redditApiService.openSub(subreddit = subreddit, mode = "hot", time = null, after = params.key,
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