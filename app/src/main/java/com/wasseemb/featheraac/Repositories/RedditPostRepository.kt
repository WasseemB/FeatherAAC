package com.wasseemb.featheraac.Repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import com.wasseemb.featheraac.Api.RedditNewsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


/**
 * Created by Wasseem on 07/12/2017.
 */
class RedditPostRepository {
  // allow queries on the main thread.
// Don't do this on a real app! See PersistenceBasicSample for an example. {
  private val redditApiService = RedditApiService.create()



  @MainThread
  fun getFrontPage(): LiveData<PagedList<RedditChildrenResponse>> {
    val sourceFactory = RedditDataSourceFactory(redditApiService)
    val config = PagedList.Config.Builder()
        .setPageSize(9)
        .setInitialLoadSizeHint(27)
        .setPrefetchDistance(9)
        .setEnablePlaceholders(true)
        .build()
    return LivePagedListBuilder(sourceFactory, config).setFetchExecutor(
        Executors.newSingleThreadExecutor()).build()
  }

  fun getRedditNewsResponse(mode: String, limit: String?, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {


    val liveData = MutableLiveData<RedditNewsResponse>()
    val call: Observable<RedditNewsResponse> = redditApiService.getNewsSort(mode, limit, after, sort,
        time)
    call.observeOn(AndroidSchedulers.mainThread()).subscribe({
      liveData.value = it
    })

    return liveData
  }

  fun openSub(subredit: String, mode: String, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {


    val liveData = MutableLiveData<RedditNewsResponse>()
    val call: Observable<RedditNewsResponse> = redditApiService.openSub(subredit, mode, after, sort,
        time)
    call.observeOn(AndroidSchedulers.mainThread()).subscribe({
      liveData.value = it
    })

    return liveData
  }
}
