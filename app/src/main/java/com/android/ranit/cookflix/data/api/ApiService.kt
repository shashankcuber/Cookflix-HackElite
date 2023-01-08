package com.android.ranit.cookflix.data.api

import com.android.ranit.cookflix.data.model.response.FoodItemsResponse
import com.android.ranit.cookflix.data.model.response.IngredientResponse
import com.android.ranit.cookflix.data.model.response.RecommendationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrls.GET_FOOD_ITEMS_LIST)
    suspend fun getFoodItems() : Response<FoodItemsResponse>

    @GET(ApiUrls.GET_INGREDIENTS_LIST)
    suspend fun getIngredients() : Response<IngredientResponse>

    @GET(ApiUrls.GET_RECOMMENDATION)
    suspend fun getRecommendations(
        @Query(ApiUrls.QUERY_INGREDIENTS)
        country : String
    ) : Response<RecommendationResponse>
}