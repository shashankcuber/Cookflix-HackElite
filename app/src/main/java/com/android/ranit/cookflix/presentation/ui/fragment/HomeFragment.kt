package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.databinding.FragmentHomeBinding
import com.android.ranit.cookflix.presentation.ui.activity.MainActivity
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val fragmentTag: String? = HomeFragment::class.simpleName

    private lateinit var mBinding : FragmentHomeBinding
    private lateinit var mViewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentHomeBinding.bind(view)
        mViewModel = (activity as MainActivity).mHomeViewModel

        initRecyclerView()
        fetchDataFromNetwork()
        attachObserver()
    }

    private fun initRecyclerView() {
        Log.d(fragmentTag, "initRecyclerView() called")
    }

    private fun fetchDataFromNetwork() {
        Log.d(fragmentTag, "fetchDataFromNetwork() called")
    }

    private fun attachObserver() {
        Log.d(fragmentTag, "attachObserver() called")
    }
}