package com.android.ranit.cookflix.presentation.viewmodel.home

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.usecase.GetFoodItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.android.ranit.cookflix.data.model.response.Result

class HomeViewModel(
    private val getFoodItemsUseCase: GetFoodItemsUseCase
): ViewModel() {
    val mFoodItemsMLD : MutableLiveData<Resource<FoodItemsResponse>> = MutableLiveData()

    fun getFoodItems() = viewModelScope.launch(Dispatchers.IO) {
        mFoodItemsMLD.postValue(Resource.loading(null))

        val apiResult = getFoodItemsUseCase.execute()
        mFoodItemsMLD.postValue(apiResult)
    }
}