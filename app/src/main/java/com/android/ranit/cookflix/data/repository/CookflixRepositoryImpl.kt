package com.android.ranit.cookflix.data.repository

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.repository.dataSource.NetworkDataSource
import com.android.ranit.cookflix.data.utils.Resource
import com.android.ranit.cookflix.domain.repository.CookflixRepository
import retrofit2.Response

/**
 * Implements the Repository Interface defined in the Domain Layer
 */
class CookflixRepositoryImpl(private val networkDataSource: NetworkDataSource) : CookflixRepository {
    // Food-Items
    override suspend fun getFoodItems(): Resource<FoodItemsResponse> {
        return foodItemsResponseToResource(networkDataSource.getFoodItems())
    }

    private fun foodItemsResponseToResource(apiResponse: Response<FoodItemsResponse>)
        : Resource<FoodItemsResponse> {
        if (apiResponse.isSuccessful) {
            apiResponse.body().let {
                return Resource.success(it)
            }
        }
        return Resource.error(
            apiResponse.message(),
            null
        )
    }

    // Ingredients
    override suspend fun getIngredients(): Resource<IngredientResponse> {
        return ingredientsResponseToResource(networkDataSource.getIngredients())
    }

    private fun ingredientsResponseToResource(apiResponse: Response<IngredientResponse>)
        : Resource<IngredientResponse> {
        if (apiResponse.isSuccessful) {
            apiResponse.body().let {
                return Resource.success(it)
            }
        }
        return Resource.error(
            apiResponse.message(),
            null
        )
    }
}