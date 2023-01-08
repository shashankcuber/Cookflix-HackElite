package com.android.ranit.cookflix.presentation.viewmodel.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.usecase.GetIngredientsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientsViewModel(
    private val getIngredientsUseCase: GetIngredientsUseCase
): ViewModel() {
    val mIngredientsMLD : MutableLiveData<Resource<IngredientResponse>> = MutableLiveData()

    fun getIngredients() = viewModelScope.launch(Dispatchers.IO) {
        mIngredientsMLD.postValue(Resource.loading(null))

        val apiResult = getIngredientsUseCase.execute()
        mIngredientsMLD.postValue(apiResult)
    }
}