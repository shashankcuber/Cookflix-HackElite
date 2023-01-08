package com.android.ranit.cookflix.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.databinding.FragmentCookBinding
import com.android.ranit.cookflix.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CookFragment : Fragment() {
    private val fragmentTag: String? = CookFragment::class.simpleName

    private lateinit var mBinding : FragmentCookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cook, container, false)
    }
}