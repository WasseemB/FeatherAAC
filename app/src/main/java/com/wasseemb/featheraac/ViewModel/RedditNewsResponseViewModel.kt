package com.wasseemb.featheraac.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.support.annotation.NonNull
import com.wasseemb.featheraac.Api.RedditNewsResponse
import com.wasseemb.featheraac.Repositories.RedditPostRepository


/**
 * Created by Wasseem on 07/12/2017.
 */

class RedditNewsResponseViewModel(application: Application) : AndroidViewModel(application) {
  private var mApiResponse: MediatorLiveData<RedditNewsResponse> = MediatorLiveData()
  private var redditPostRepository: RedditPostRepository = RedditPostRepository(
      application.applicationContext)
  private val subreddit = MutableLiveData<String>()
  private val frontPage = MutableLiveData<String>()


  @NonNull
  fun getApiResponse(): LiveData<RedditNewsResponse> {
    return mApiResponse as LiveData<RedditNewsResponse>
  }

  fun loadFrontPage(mode: String, limit: String?, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {
    val mApiResponse = mApiResponse
    mApiResponse.addSource(
        redditPostRepository.getRedditNewsResponse(mode, limit, after, sort, time),
        { apiResponse -> mApiResponse.value = apiResponse }
    )
    return mApiResponse

  }

  fun loadFrontPageX(mode: String, limit: String?, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {
    val liveList = Transformations.switchMap(this.frontPage) { _ ->
      redditPostRepository.getRedditNewsResponse(mode, limit, after, sort, time)
    }
    return liveList
  }

  fun setFront(frontPage: String) {
    this.frontPage.value = frontPage
  }

  fun setSub(subreddit: String) {
    this.subreddit.value = subreddit
  }

  fun loadSub(subreddit: String, mode: String = "hot", after: String? = "null",
      sort: String? = "null",
      time: String? = null): LiveData<RedditNewsResponse> {
    val liveList = Transformations.switchMap(this.subreddit) { sub ->
      redditPostRepository.openSub(sub, mode, after, sort, time)
    }
    return liveList
  }

}