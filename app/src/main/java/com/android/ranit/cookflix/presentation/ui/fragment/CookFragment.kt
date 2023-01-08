package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.data.cache.DataManager
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
        attachListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mSelectedIngredientsList.clear()
    }

    private fun initRecyclerView() {
        Log.d(fragmentTag, "initRecyclerView() called")
        mBinding.rvIngredients.apply {
            adapter = mIngredientsAdapter
            layoutManager = StaggeredGridLayoutManager(
                2,
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
        // Ingredients
        mViewModel.mIngredientsMLD.observe(viewLifecycleOwner) {
            when(it.state) {
                State.LOADING -> {
                    mBinding.shimmerIngredients.visibility = View.VISIBLE
                    mBinding.shimmerIngredients.startShimmer()

                    mBinding.tvHeader.visibility = View.INVISIBLE
                    mBinding.acIngredients.visibility = View.INVISIBLE
                    mBinding.tvSelectedIngredients.visibility = View.INVISIBLE
                    mBinding.rvIngredients.visibility = View.INVISIBLE
                }

                State.SUCCESS -> {
                    mBinding.shimmerIngredients.stopShimmer()
                    mBinding.shimmerIngredients.visibility = View.GONE

                    mBinding.tvHeader.visibility = View.VISIBLE
                    mBinding.acIngredients.visibility = View.VISIBLE
                    mBinding.tvSelectedIngredients.visibility = View.VISIBLE
                    mBinding.rvIngredients.visibility = View.VISIBLE

                    lateinit var acAdapter: ArrayAdapter<String>
                    it.data?.let { data ->
                        mIngredientsList = data.ingredientList.toList()
                        acAdapter = ArrayAdapter(
                            requireContext(),
                            R.layout.item_autocomplete,
                            R.id.tv_item_autocomplete,
                            mIngredientsList
                        )
                    }
                    mBinding.acIngredients.threshold = 2
                    mBinding.acIngredients.setAdapter(acAdapter)
                }

                State.ERROR -> {
                    mBinding.shimmerIngredients.visibility = View.GONE
                    mBinding.tvHeader.visibility = View.INVISIBLE
                    mBinding.acIngredients.visibility = View.INVISIBLE
                    mBinding.tvSelectedIngredients.visibility = View.INVISIBLE
                    mBinding.rvIngredients.visibility = View.INVISIBLE

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

    private fun attachListeners() {
        Log.d(fragmentTag, "attachListeners() called")
        mBinding.acIngredients.onItemClickListener =
            OnItemClickListener { parent, _, position, _ ->
                val selection = parent.getItemAtPosition(position) as String

                // Update adapter
                if (selection.isNotEmpty()) {
                    mSelectedIngredientsList.add(selection)
                    mIngredientsAdapter.setData(mSelectedIngredientsList)
                }
            }

        mBinding.btnRecommend.setOnClickListener {
            DataManager.setIngredientData(mSelectedIngredientsList)
            Navigation.findNavController(requireView()).navigate(R.id.action_cookFragment_to_recipeRecommendationFragment)
        }
    }

}