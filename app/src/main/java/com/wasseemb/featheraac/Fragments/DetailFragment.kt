package com.wasseemb.featheraac.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wasseemb.featheraac.R.layout
import kotlinx.android.synthetic.main.fragment_detail.view.info_image

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

  // TODO: Rename and change types of parameters
  private var mParam1: String? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      mParam1 = arguments!!.getString(
          ARG_PARAM1)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(layout.fragment_detail, container, false)
    Log.d("Parameter", mParam1.toString())
    Picasso.with(view.context).load(mParam1).into(view.info_image)
    return view
  }

  companion object {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private val ARG_PARAM1 = "thumbnail"

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    fun newInstance(param1: String, param2: String): DetailFragment {
      val fragment = DetailFragment()
      val args = Bundle()
      args.putString(ARG_PARAM1, param1)
      fragment.arguments = args
      return fragment
    }

  }

}// Required empty public constructor
