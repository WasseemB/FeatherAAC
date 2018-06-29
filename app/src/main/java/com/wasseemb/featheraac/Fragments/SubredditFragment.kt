package com.wasseemb.featheraac.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter.ItemClickListener
import com.wasseemb.featheraac.Adapter.InfiniteScrollListener
import com.wasseemb.featheraac.Api.RedditNewsResponse
import com.wasseemb.featheraac.R
import com.wasseemb.featheraac.R.layout
import com.wasseemb.featheraac.ViewModel.RedditNewsResponseViewModel

/**
 * A simple [Fragment] subclass.
 **/

class SubredditFragment : Fragment() {
  lateinit var recyclerView: RecyclerView
  //lateinit var swipeRefreshLayout: SwipeRefreshLayout
  lateinit var viewModel: RedditNewsResponseViewModel
  lateinit var adapter: PostsAdapter
  lateinit var itemClickListener: ItemClickListener


  var after: String? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(layout.fragment_main, container, false)
    viewModel = ViewModelProviders.of(this).get(RedditNewsResponseViewModel::class.java)
    setUpRecyclerView(view)
    if (arguments != null) {
//      openSub(arguments!!.getString(
//          ARG_CAUGHT))
    }
    // Inflate the layout for this fragment

    return view
  }




  private fun setUpRecyclerView(view: View) {
    recyclerView = view.findViewById(R.id.recyclerview)
    recyclerView.layoutManager = LinearLayoutManager(
        activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
    recyclerView.setHasFixedSize(true)
    recyclerView.setItemViewCacheSize(10)
    recyclerView.isDrawingCacheEnabled = true
    recyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
    val layoutManager = LinearLayoutManager(activity?.applicationContext,
        LinearLayout.VERTICAL, false)
    recyclerView.layoutManager = layoutManager
    adapter = PostsAdapter()
    adapter.picasso = Picasso.with(view.context)
    val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
        layoutManager.orientation)
    recyclerView.addItemDecoration(dividerItemDecoration)
    adapter.itemClickListener = itemClickListener
    recyclerView.adapter = adapter//To change body of created functions use File | Settings | File Templates.
  }


//  fun openSub(sub: String) {
//    val observer = Observer<RedditNewsResponse> { t ->
//      t?.data?.children?.let {
//        recyclerView.post {
//          // adapter.itemList += t.data.children
//          adapter.submitList(t.data.children)
//          Log.d("MainActivity", "AndroidSub")
//          after = t.data.after
//          //swipeRefreshLayout.isRefreshing = false
//
//        }
//      }
//    }
//
//    viewModel.loadSub("android").observe(this, observer)
//    recyclerView.addOnScrollListener(
//        InfiniteScrollListener({
//          viewModel.loadSub(subreddit = "android", mode = "hot", time = null, after = after,
//              sort = null).observe(this, observer)
//        }, recyclerView.layoutManager as LinearLayoutManager)
//    )
//
//    viewModel.setSub(sub)
//  }

  companion object {
    private val ARG_CAUGHT = "sub"

    fun newInstance(sub: String): SubredditFragment {
      val args: Bundle = Bundle()
      args.putSerializable(ARG_CAUGHT, sub)
      val fragment = SubredditFragment()
      fragment.arguments = args
      return fragment
    }
  }

}


// Required empty public constructor
