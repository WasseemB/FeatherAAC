package com.wasseemb.featheraac

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.wasseemb.featheraac.Fragments.DetailFragment

/**
 * Created by Wasseem on 10/01/2018.
 */
class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)
    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)

    // Show the Up button in the action bar.
    val actionBar = supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(true)

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val fragment = DetailFragment.newInstance(intent.getStringExtra("thumbnail"), "")
      // fragment.setArguments(arguments)
      supportFragmentManager.beginTransaction().add(R.id.detailPanel, fragment).commit()
    }
  }
}