package com.android.ranit.cookflix.data.cache

object DataManager {
    private var mSelectedIngredientsList: ArrayList<String> = ArrayList()

    fun setIngredientData(data: ArrayList<String>) {
        mSelectedIngredientsList = data
    }

    fun getIngredientData(): ArrayList<String> {
        return mSelectedIngredientsList
    }

    fun clearIngredientData() {
        mSelectedIngredientsList.clear()
    }
}