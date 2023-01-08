package com.android.ranit.cookflix.presentation.viewmodel.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientsViewModel(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase
): ViewModel() {
    val mIngredientsMLD : MutableLiveData<Resource<IngredientResponse>> = MutableLiveData()
    fun getIngredients() = viewModelScope.launch(Dispatchers.IO) {
        mIngredientsMLD.postValue(Resource.loading(null))

        val apiResult = getIngredientsUseCase.execute()
        mIngredientsMLD.postValue(apiResult)
    }

    val mRecommendationsMLD : MutableLiveData<Resource<RecommendationResponse>> = MutableLiveData()
    fun getRecommendations(ingredients: String) = viewModelScope.launch(Dispatchers.IO) {
        mRecommendationsMLD.postValue(Resource.loading(null))

        val apiResult = getRecommendationUseCase.execute(ingredients)
        mRecommendationsMLD.postValue(apiResult)
    }
}