@file:JvmName("ExtensionsUtils")

package com.wasseemb.featheraac.Extensions

import android.support.v7.util.DiffUtil
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.wasseemb.FeatherForReddit.Adapter.PostsAdapter
import com.wasseemb.featheraac.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Date
import java.util.concurrent.TimeUnit


/**
 * Created by Wasseem on 03/08/2017.
 */

inline fun  ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String?) {
  Picasso.with(context)
      .load(imageUrl)
      .resize(60, 60)
      .centerCrop()
      .into(this)

  //Glide.with(context).load(imageUrl).into(this)
}


fun Date.timeFromNow(): String {
  val date = Date()
  val duration = date.time - this.time
  val diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration)
  val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration)
  val diffInHours = TimeUnit.MILLISECONDS.toHours(duration)
  val diffInDays = TimeUnit.MILLISECONDS.toDays(duration)
  if (diffInSeconds < 60) return diffInSeconds.toString() + "s"
  if (diffInMinutes < 60) return diffInMinutes.toString() + "m"
  if (diffInHours < 24) return diffInHours.toString() + "h"
  if (diffInDays < 31) return diffInHours.toString() + "d"
  return "Out of range"
}

fun Double.roundTwoDigits(): String {
  val df = DecimalFormat("#.##")
  df.roundingMode = RoundingMode.CEILING
  return df.format(this)
}


fun numToK(number: Double): String {
  if (number > 1000) return (number / 1000).roundTwoDigits() + "k"
  else return number.toInt().toString()

}


fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>,
    compare: (T, T) -> Boolean) {
  val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return compare(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
  })

  diff.dispatchUpdatesTo(this)
}

