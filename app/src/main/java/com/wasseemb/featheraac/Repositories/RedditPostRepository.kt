package com.wasseemb.featheraac.Repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import com.wasseemb.featheraac.Api.RedditApiService
import com.wasseemb.featheraac.Api.RedditNewsResponse
import com.wasseemb.featheraac.Database.RedditDb
import com.wasseemb.featheraac.Database.RedditPostDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Wasseem on 07/12/2017.
 */
class RedditPostRepository// allow queries on the main thread.
// Don't do this on a real app! See PersistenceBasicSample for an example.
(context: Context) {

  val BASE_URL = "https://www.reddit.com"
  private val apiService: RedditApiService

  private var db: RedditDb? = null
  private var repoDao: RedditPostDao? = null
  private var redditApiService: RedditApiService? = null

  init {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        //.addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
    apiService = retrofit.create<RedditApiService>(RedditApiService::class.java)

    this.db = Room.databaseBuilder(context.applicationContext, RedditDb::class.java,
        "posts").allowMainThreadQueries()
        // allow queries on the main thread.
        // Don't do this on a real app! See PersistenceBasicSample for an example.
        .build()
    this.repoDao = db!!.posts()
    this.redditApiService = apiService
  }


  fun getRedditNewsResponse(mode: String, limit: String?, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {


    val liveData = MutableLiveData<RedditNewsResponse>()
    val call: Observable<RedditNewsResponse> = apiService.getNewsSort(mode, limit, after, sort,
        time)
    call.observeOn(AndroidSchedulers.mainThread()).subscribe({
      liveData.value = it
    })

    return liveData
  }

  fun openSub(subredit: String, mode: String, after: String?, sort: String?,
      time: String?): LiveData<RedditNewsResponse> {


    val liveData = MutableLiveData<RedditNewsResponse>()
    val call: Observable<RedditNewsResponse> = apiService.openSub(subredit, mode, after, sort,
        time)
    call.observeOn(AndroidSchedulers.mainThread()).subscribe({
      liveData.value = it
    })

    return liveData
  }


//
//   fun getRedditNewsResponse(mode: String, limit: String?, after: String?, sort: String?,
//      time: String?): LiveData<Resource<RedditNewsResponse>> {
//    return object : NetworkBoundResource<RedditNewsResponse, RedditNewsResponse>(appExecutors) {
//      override fun saveCallResult(@NonNull item: RedditNewsResponse) {
//        val posts = ArrayList<RedditPost>()
//        item.data.children.forEach {
//          posts.add(it.data)
//        }
//        repoDao?.insert(posts)
//        posts.clear()
//
//
//      }
//
//      override fun shouldFetch(@Nullable data: RedditNewsResponse?): Boolean {
//         return data == null
//      }
//
//      @NonNull
//      override fun loadFromDb(): LiveData<RedditNewsResponse> {
//        val x = ArrayList<RedditChildrenResponse>()
//        repoDao!!.getAllPosts().forEach {
//            x.add(RedditChildrenResponse(it))
//          }
//        if(!x.isEmpty()) {
//          val redditChildrenResponse = RedditDataResponse("0", "0", x, "t3_" + x.last().data.id,
//              "0")
//          val redditNewsResponse = RedditNewsResponse(redditChildrenResponse)
//          val lv = MutableLiveData<RedditNewsResponse>()
//          lv.postValue(redditNewsResponse)
//          x.clear()
//          return lv
//        }
//        else
//        {
//          val lv = MutableLiveData<RedditNewsResponse>()
//          lv.postValue(null)
//          x.clear()
//          return lv
//        }
//      }
//
//      @NonNull
//      override fun createCall(): LiveData<ApiResponse<RedditNewsResponse>> {
//        return redditApiService!!.getNewsSorst(mode, limit, after, sort,
//        time)
//      }
//    }.asLiveData()
//  }
}
