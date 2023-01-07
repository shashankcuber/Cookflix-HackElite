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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag : String? = MainActivity::class.simpleName

    @Inject
    lateinit var mHomeViewModelFactory: HomeViewModelFactory
    lateinit var mHomeViewModel: HomeViewModel

    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mNavHostFragment: NavHostFragment
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initViews()
        initViewModel()
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

    private fun initViewModel() {
        Log.d(tag, "initViewModel() called")
        mHomeViewModel = ViewModelProvider(
            this,
            mHomeViewModelFactory
        )[HomeViewModel::class.java]
    }
}