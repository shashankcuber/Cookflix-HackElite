package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.data.cache.DataManager
import com.android.ranit.cookflix.data.model.response.Recommendation
import com.android.ranit.cookflix.data.utils.State
import com.android.ranit.cookflix.databinding.FragmentHomeBinding
import com.android.ranit.cookflix.databinding.FragmentRecipeRecommendationBinding
import com.android.ranit.cookflix.presentation.ui.activity.MainActivity
import com.android.ranit.cookflix.presentation.ui.adapter.RecommendationAdapter
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModel
import com.android.ranit.cookflix.presentation.viewmodel.recommendations.RecommendationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeRecommendationFragment : Fragment() {
    private val fragmentTag: String? = RecipeRecommendationFragment::class.simpleName

    private lateinit var mBinding : FragmentRecipeRecommendationBinding
    private lateinit var mViewModel : RecommendationsViewModel

    @Inject
    lateinit var mRecommendationAdapter: RecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentRecipeRecommendationBinding.bind(view)
        mViewModel = (activity as MainActivity).mRecommendationsViewModel

        initRecyclerView()
        fetchDataFromNetwork()
        attachObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DataManager.clearIngredientData()
    }

    private fun initRecyclerView() {
        Log.d(fragmentTag, "initRecyclerView() called")
        mBinding.rvRecommendation.apply {
            adapter = mRecommendationAdapter
            layoutManager = LinearLayoutManager(activity)

            mRecommendationAdapter.onItemClickCallback = { data, position ->
                Log.e(fragmentTag, "onItemClickCallback: position = $position && data = $data")
                // Navigation.findNavController(requireView()).navigate(R.id.action_cookFragment_to_recipeRecommendationFragment)
            }
        }
    }

    private fun fetchDataFromNetwork() {
        mViewModel.getRecommendations(DataManager.getIngredientData().toString())
    }

    private fun attachObserver() {
        // Recommendations
        mViewModel.mRecommendationsMLD.observe(viewLifecycleOwner) {
            when(it.state) {
                State.LOADING -> {
                    //mBinding.shimmerIngredients.visibility = View.VISIBLE
                    //mBinding.shimmerIngredients.startShimmer()
                }

                State.SUCCESS -> {
                   // mBinding.shimmerIngredients.stopShimmer()
                   // mBinding.shimmerIngredients.visibility = View.GONE

                    it.data?.let { data ->
                        val recommendationList: ArrayList<Recommendation> = ArrayList(data.Recommendation_response)
                        mRecommendationAdapter.setData(recommendationList)
                    }
                }

                State.ERROR -> {
                   // mBinding.shimmerIngredients.visibility = View.GONE

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