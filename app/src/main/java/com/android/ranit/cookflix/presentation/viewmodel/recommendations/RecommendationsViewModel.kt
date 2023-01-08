package com.android.ranit.cookflix.presentation.viewmodel.recommendations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.usecase.GetRecommendationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecommendationsViewModel(
    private val getRecommendationUseCase: GetRecommendationUseCase
): ViewModel() {

    val mRecommendationsMLD : MutableLiveData<Resource<RecommendationResponse>> = MutableLiveData()
    fun getRecommendations(ingredients: String) = viewModelScope.launch(Dispatchers.IO) {
        mRecommendationsMLD.postValue(Resource.loading(null))

        val apiResult = getRecommendationUseCase.execute(ingredients)
        mRecommendationsMLD.postValue(apiResult)
    }
}