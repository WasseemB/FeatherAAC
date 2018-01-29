package com.wasseemb.featheraac

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter.ItemClickListener
import com.wasseemb.featheraac.Api.RedditChildrenResponse
import com.wasseemb.featheraac.Fragments.MainFragment
import com.wasseemb.featheraac.Fragments.SubredditFragment
import com.wasseemb.featheraac.R.string
import com.wasseemb.featheraac.ViewModel.RedditNewsResponseViewModel


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ItemClickListener {
  override fun onItemClick(redditChildrenResponse: RedditChildrenResponse) {
//    supportFragmentManager.beginTransaction().replace(R.id.firstPanel,
//        DetailFragment()).addToBackStack(null).commit()
    val intent = Intent(this, DetailActivity::class.java)
    intent.putExtra("thumbnail", redditChildrenResponse.data.url)
    startActivity(intent)

    Log.d("MainActivity", "ItemClicked")
//To change body of created functions use File | Settings | File Templates.
  }

  val homeFragment: MainFragment = MainFragment()
  lateinit var viewModel: RedditNewsResponseViewModel
  lateinit var contextx: Context

  override fun onNavigationItemSelected(item: MenuItem): Boolean {


    val fragment: Fragment
    when (item.itemId) {
      R.id.all -> {
        fragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.firstPanel, fragment,
            fragment.tag).addToBackStack(null).commit()
        fragment.itemClickListener = this

      }
      R.id.enterSub -> {
      }
      R.id.android -> {
        fragment = SubredditFragment.newInstance("android")
        supportFragmentManager.beginTransaction().replace(R.id.firstPanel, fragment,
            fragment.tag).addToBackStack(null).commit()
        fragment.itemClickListener = this

      }
      R.id.amd -> {
        fragment = SubredditFragment.newInstance("amd")
        supportFragmentManager.beginTransaction().replace(R.id.firstPanel, fragment,
            fragment.tag).addToBackStack(null).commit()
        fragment.itemClickListener = this

      }
    }


    val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
    drawer.closeDrawer(GravityCompat.START)
    return true
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_t)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    // swipeRefreshLayout = findViewById(R.id.swipe_container)
    viewModel = ViewModelProviders.of(this).get(RedditNewsResponseViewModel::class.java)
    val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    val toggle = ActionBarDrawerToggle(this, drawer, toolbar, string.navigation_drawer_open,
        string.navigation_drawer_close)
    drawer.addDrawerListener(toggle)
    toggle.syncState()

    val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
    navigationView.setNavigationItemSelectedListener(this)
    supportFragmentManager.beginTransaction().replace(R.id.firstPanel, homeFragment,
        homeFragment.tag).addToBackStack(null).commit()
    homeFragment.itemClickListener = this


    //setUpRecyclerView()
    //frontPage()

//    swipeRefreshLayout.setOnRefreshListener {
//      frontPage()
//    }

  }


}



