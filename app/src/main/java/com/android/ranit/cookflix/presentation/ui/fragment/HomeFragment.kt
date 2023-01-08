package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.data.utils.State
import com.android.ranit.cookflix.databinding.FragmentHomeBinding
import com.android.ranit.cookflix.presentation.ui.activity.MainActivity
import com.android.ranit.cookflix.presentation.ui.adapter.FoodItemsAdapter
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val fragmentTag: String? = HomeFragment::class.simpleName

    private lateinit var mBinding : FragmentHomeBinding
    private lateinit var mViewModel : HomeViewModel

    @Inject
    lateinit var mFoodItemsAdapter: FoodItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        mBinding.rvFoodItems.apply {
            adapter = mFoodItemsAdapter
            layoutManager = LinearLayoutManager(activity)

            mFoodItemsAdapter.onItemClickCallback = { data, position ->
                Log.e(fragmentTag, "onItemClickCallback: position = $position && data = $data")
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_foodDetailsFragment)
            }
        }
    }

    private fun fetchDataFromNetwork() {
        Log.d(fragmentTag, "fetchDataFromNetwork() called")
        mViewModel.getFoodItems()
    }

    private fun attachObserver() {
        Log.d(fragmentTag, "attachObserver() called")
        mViewModel.mFoodItemsMLD.observe(viewLifecycleOwner) {
            when(it.state) {
                State.LOADING -> {
                    mBinding.shimmerFoodItems.visibility = View.VISIBLE
                    mBinding.shimmerFoodItems.startShimmer()
                }

                State.SUCCESS -> {
                    mBinding.shimmerFoodItems.stopShimmer()
                    mBinding.shimmerFoodItems.visibility = View.GONE

                    mBinding.tvHeader.visibility = View.VISIBLE
                    mBinding.rvFoodItems.visibility = View.VISIBLE

                    it.data?.let { data ->
                        mFoodItemsAdapter.setData(data.Result.toList())
                    }
                }

                State.ERROR -> {
                    mBinding.shimmerFoodItems.visibility = View.GONE
                    mBinding.rvFoodItems.visibility = View.GONE

                    it.message?.let { message ->
                        Toast.makeText(
                            activity,
                            message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}