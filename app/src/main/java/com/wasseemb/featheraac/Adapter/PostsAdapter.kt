package com.wasseemb.FeatherForReddit.Adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.recyclerview.extensions.ListAdapter

import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter.RedditPostViewHolder
import com.wasseemb.featheraac.Adapter.RedditItemCallBack
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import com.wasseemb.featheraac.Api.RedditPost
import com.wasseemb.featheraac.Extensions.autoNotify
import com.wasseemb.featheraac.Extensions.inflate
import com.wasseemb.featheraac.Extensions.numToK
import com.wasseemb.featheraac.R
import kotlin.properties.Delegates


/**
 * Created by Wasseem on 31/07/2017.
 */
class PostsAdapter : PagedListAdapter<RedditChildrenResponse,RedditPostViewHolder>(RedditItemCallBack()) {
  lateinit var itemClickListener: ItemClickListener
  lateinit var picasso: Picasso


  interface ItemClickListener {
    fun onItemClick(redditChildrenResponse: RedditChildrenResponse)
  }



//  var itemList: List<RedditChildrenResponse> by Delegates.observable(
//      emptyList()) { _, old, new ->
//    autoNotify(old, new) { o, n -> o.data.id == n.data.id }
//  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
    val viewHolder = RedditPostViewHolder(parent.inflate(R.layout.view_item_con))
    viewHolder.itemView.setOnClickListener {
      val position = viewHolder.adapterPosition
      if (position != RecyclerView.NO_POSITION) {
        itemClickListener.onItemClick(getItem(position)!!)
      }
    }
    return viewHolder
  }


  override fun onBindViewHolder(vh: RedditPostViewHolder, position: Int) {

    vh.bindTo(getItem(position)!!.data, picasso)
  }


  override fun onViewRecycled(holder: RedditPostViewHolder) {
    if (holder != null) {
      picasso.cancelRequest(holder.imageView)
      holder.imageView.visibility = View.VISIBLE

    }
    super.onViewRecycled(holder)
  }


  class RedditPostViewHolder(itemView: View) : ViewHolder(itemView) {

    val author: TextView = itemView.findViewById(R.id.author)
    val domain: TextView = itemView.findViewById(R.id.domain)
    val commentCount: TextView = itemView.findViewById(R.id.commentCount)
    val score: TextView = itemView.findViewById(R.id.score)
    val subreddit: TextView = itemView.findViewById(R.id.subreddit)
    val created_utc: TextView = itemView.findViewById(R.id.created_utc)
    val title = itemView.findViewById<TextView>(R.id.title)
    val imageView: ImageView = itemView.findViewById(R.id.thumbnail)

    fun bindTo(redditItem: RedditPost, picasso: Picasso) {

      title.text = redditItem.title
      if (URLUtil.isValidUrl(redditItem.thumbnail)) {
        picasso.load(redditItem.thumbnail).resize(60, 60).placeholder(
            R.color.colorPrimaryLight).centerCrop().into(imageView)
        //imageView.loadImg(redditItem.thumbnail)
      } else {
        imageView.visibility = View.GONE
      }
      author.text = "/u/" + redditItem.author
      subreddit.text = "/r/" + redditItem.subreddit
      domain.text = redditItem.domain
      commentCount.text = String.format(
          commentCount.context.resources.getString(R.string.comment_count_message),
          numToK(redditItem.num_comments))
      score.text = String.format(score.context.resources.getString(R.string.score_count_message),
          numToK(redditItem.score))
//      val postDate = Date((redditItem.created_utc) * 1000)
      created_utc.text = redditItem.created_utc.toString()

    }
  }


}