package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.data.utils.State
import com.android.ranit.cookflix.databinding.FragmentCookBinding
import com.android.ranit.cookflix.presentation.ui.activity.MainActivity
import com.android.ranit.cookflix.presentation.ui.adapter.IngredientsAdapter
import com.android.ranit.cookflix.presentation.viewmodel.ingredients.IngredientsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CookFragment : Fragment() {
    private val fragmentTag: String? = CookFragment::class.simpleName

    private lateinit var mBinding : FragmentCookBinding
    private lateinit var mViewModel : IngredientsViewModel

    @Inject
    lateinit var mIngredientsAdapter: IngredientsAdapter

    private var mIngredientsList: List<String> = ArrayList()
    private var mSelectedIngredientsList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentCookBinding.bind(view)
        mViewModel = (activity as MainActivity).mIngredientsViewModel

        initRecyclerView()
        fetchDataFromNetwork()
        attachObserver()
    }

    private fun initRecyclerView() {
        Log.d(fragmentTag, "initRecyclerView() called")
        mBinding.rvIngredients.apply {
            adapter = mIngredientsAdapter
            layoutManager = StaggeredGridLayoutManager(
                3,
                LinearLayoutManager.HORIZONTAL
            )
        }
    }

    private fun fetchDataFromNetwork() {
        Log.d(fragmentTag, "fetchDataFromNetwork() called")
        mViewModel.getIngredients()
    }

    private fun attachObserver() {
        Log.d(fragmentTag, "attachObserver() called")
        mViewModel.mIngredientsMLD.observe(viewLifecycleOwner) {
            when(it.state) {
                State.LOADING -> {
                    mBinding.shimmerIngredients.visibility = View.VISIBLE
                    mBinding.shimmerIngredients.startShimmer()
                }

                State.SUCCESS -> {
                    mBinding.shimmerIngredients.stopShimmer()
                    mBinding.shimmerIngredients.visibility = View.GONE

                    mBinding.tvHeader.visibility = View.VISIBLE
                    //mBinding.autoCompleteIngredient.visibility = View.VISIBLE
                    mBinding.tvSelectedIngredients.visibility = View.VISIBLE
                    mBinding.rvIngredients.visibility = View.VISIBLE

                    it.data?.let { data ->
                        //mIngredientsList = data.ingredientList.toList()
                        Log.d(fragmentTag, "RRG attachObserver() called with: mIngredientsList size = ${data.ingredientList?.size}")
                    }

                    // TODO: Adding selected item into ingredient list onClick
                    mIngredientsAdapter.setData(mSelectedIngredientsList)
                }

                State.ERROR -> {
                    mBinding.shimmerIngredients.visibility = View.GONE
                    mBinding.tvHeader.visibility = View.VISIBLE
                    //mBinding.autoCompleteIngredient.visibility = View.VISIBLE
                    mBinding.tvSelectedIngredients.visibility = View.VISIBLE
                    mBinding.rvIngredients.visibility = View.VISIBLE

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