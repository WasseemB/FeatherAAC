package com.wasseemb.featheraac.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.PagedList
import android.support.annotation.NonNull
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import com.wasseemb.featheraac.Api.RedditNewsResponse
import com.wasseemb.featheraac.Repositories.RedditPostRepository


/**
 * Created by Wasseem on 07/12/2017.
 */

class RedditNewsResponseViewModel(application: Application) : AndroidViewModel(application) {
//  private var mApiResponse: MediatorLiveData<RedditNewsResponse> = MediatorLiveData()
  private val redditPostRepository = RedditPostRepository()
  private val subreddit = MutableLiveData<String>()
  private val frontPage = MutableLiveData<String>()


//  fun loadFrontPage(mode: String, limit: String?, after: String?, sort: String?,
//      time: String?): LiveData<RedditNewsResponse> {
//    val mApiResponse = mApiResponse
//    mApiResponse.addSource(
//        redditPostRepository.getRedditNewsResponse(mode, limit, after, sort, time),
//        { apiResponse -> mApiResponse.value = apiResponse }
//    )
//    return mApiResponse
//
//  }


  fun getFrontPage(): LiveData<PagedList<RedditChildrenResponse>> {
    return Transformations.switchMap(frontPage) {
      redditPostRepository.getFrontPage()
    }
  }

  fun setFront(frontPage: String) {
    this.frontPage.value = frontPage
  }

  fun setSub(subreddit: String) {
    this.subreddit.value = subreddit
  }

  fun loadSub(): LiveData<PagedList<RedditChildrenResponse>> {
    return Transformations.switchMap(this.subreddit) { sub ->
      redditPostRepository.getSubReddit(sub)
    }
  }

}