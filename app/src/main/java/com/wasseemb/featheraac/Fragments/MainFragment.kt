package com.wasseemb.featheraac.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter.ItemClickListener
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import com.wasseemb.featheraac.R
import com.wasseemb.featheraac.R.layout
import com.wasseemb.featheraac.ViewModel.RedditNewsResponseViewModel


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
  lateinit var recyclerView: RecyclerView
  //lateinit var swipeRefreshLayout: SwipeRefreshLayout
  lateinit var viewModel: RedditNewsResponseViewModel
  lateinit var adapter: PostsAdapter
  lateinit var itemClickListener: ItemClickListener
  var after: String? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(layout.fragment_main, container, false)
    viewModel = ViewModelProviders.of(this).get(RedditNewsResponseViewModel::class.java)
    setUpRecyclerView(view)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observeViewModel(viewModel)
    viewModel.setFront("FrontPage")


    //frontPage()
  }


  private fun observeViewModel(viewModel: RedditNewsResponseViewModel) {
    viewModel.getFrontPage().observe(this, Observer<PagedList<RedditChildrenResponse>> {
      adapter.submitList(it)
    })
  }

  private fun setUpRecyclerView(view: View) {
    recyclerView = view.findViewById(R.id.recyclerview)
    recyclerView.layoutManager = LinearLayoutManager(
        activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
    recyclerView.setHasFixedSize(true)
    recyclerView.setItemViewCacheSize(20)
    recyclerView.isDrawingCacheEnabled = true
    recyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
    val layoutManager = LinearLayoutManager(activity!!.applicationContext,
        LinearLayout.VERTICAL, false)
    recyclerView.layoutManager = layoutManager
    adapter = PostsAdapter()
    adapter.itemClickListener = itemClickListener
    adapter.picasso = Picasso.with(view.context)
    val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
        layoutManager.orientation)
    recyclerView.addItemDecoration(dividerItemDecoration)
    recyclerView.adapter = adapter//To change body of created functions use File | Settings | File Templates.
  }

//  private fun frontPage() {
//    val observer = Observer<RedditNewsResponse> { t ->
//      t?.data?.children?.let {
//        recyclerView.post {
//          adapter.submitList(t.data.children)
//          //adapter.itemList += t.data.children
//          Log.d("MainActivity", "frontPage")
//          after = t.data.after
//          //swipeRefreshLayout.isRefreshing = false
//
//        }
//      }
//    }
//    viewModel.loadFrontPageX(mode = "hot", time = null, after = after, limit = null,
//        sort = null).observe(this, observer)
//    recyclerView.addOnScrollListener(
//        InfiniteScrollListener({
//          viewModel.loadFrontPageX(mode = "hot", time = null, after = after, limit = null,
//              sort = null).observe(this, observer)
//        }, recyclerView.layoutManager as LinearLayoutManager)
//    )
//    viewModel.setFront("FrontPage")
//  }
}


// Required empty public constructor
