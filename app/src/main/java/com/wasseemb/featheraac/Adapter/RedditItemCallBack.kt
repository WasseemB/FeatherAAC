package com.wasseemb.featheraac.Adapter

import android.support.v7.util.DiffUtil
import com.wasseemb.featheraac.Api.RedditChildrenResponse

class RedditItemCallBack : DiffUtil.ItemCallback<RedditChildrenResponse>() {
  override fun areContentsTheSame(oldItem: RedditChildrenResponse?,
      newItem: RedditChildrenResponse?): Boolean {
    return oldItem?.data?.id == newItem?.data?.id
  }

  override fun areItemsTheSame(oldItem: RedditChildrenResponse?,
      newItem: RedditChildrenResponse?): Boolean {
    return oldItem == newItem
  }

}