package com.android.ranit.cookflix.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.databinding.ActivityMainBinding
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModel
import com.android.ranit.cookflix.presentation.viewmodel.home.HomeViewModelFactory
import com.android.ranit.cookflix.presentation.viewmodel.ingredients.IngredientsViewModel
import com.android.ranit.cookflix.presentation.viewmodel.ingredients.IngredientsViewModelFactory
import com.android.ranit.cookflix.presentation.viewmodel.recommendations.RecommendationsViewModel
import com.android.ranit.cookflix.presentation.viewmodel.recommendations.RecommendationsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag : String? = MainActivity::class.simpleName

    @Inject
    lateinit var mHomeViewModelFactory: HomeViewModelFactory
    lateinit var mHomeViewModel: HomeViewModel

    @Inject
    lateinit var mIngredientsViewModelFactory: IngredientsViewModelFactory
    lateinit var mIngredientsViewModel: IngredientsViewModel

    @Inject
    lateinit var mRecommendationsViewModelFactory: RecommendationsViewModelFactory
    lateinit var mRecommendationsViewModel: RecommendationsViewModel

    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mNavHostFragment: NavHostFragment
    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initViews()
        initViewModels()
    }

    private fun initViews() {
        Log.d(tag, "initViews() called")
        // Initializing Nav-Host Fragment
        mNavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        mNavController = mNavHostFragment.navController

        // Binding the Nav-Controller
        mBinding.bottomNavigationView.setupWithNavController(
            mNavController
        )
    }

    private fun initViewModels() {
        Log.d(tag, "initViewModels() called")
        mHomeViewModel = ViewModelProvider(
            this,
            mHomeViewModelFactory
        )[HomeViewModel::class.java]

        mIngredientsViewModel = ViewModelProvider(
            this,
            mIngredientsViewModelFactory
        )[IngredientsViewModel::class.java]

        mRecommendationsViewModel = ViewModelProvider(
            this,
            mRecommendationsViewModelFactory
        )[RecommendationsViewModel::class.java]
    }
}