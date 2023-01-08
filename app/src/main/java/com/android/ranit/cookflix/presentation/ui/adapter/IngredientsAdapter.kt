package com.android.ranit.cookflix.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ranit.cookflix.databinding.ItemIngredientBinding

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.ViewHolderIngredient>() {
    private val tag = IngredientsAdapter::class.simpleName
    private val mArrayListIngredients = ArrayList<String>()

    fun setData(newIngredientsList: List<String>) {
        Log.d(tag, "setData() called of size = ${newIngredientsList.size}")
        val diffUtilCallback = IngredientsDiffUtilCallback(
            mArrayListIngredients,
            newIngredientsList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        mArrayListIngredients.clear()
        mArrayListIngredients.addAll(newIngredientsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngredient {
        val binding = ItemIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderIngredient(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderIngredient, position: Int) {
        val curIngredient = mArrayListIngredients[position]
        holder.bindViews(curIngredient)
    }

    override fun getItemCount(): Int {
        return mArrayListIngredients.size
    }

    inner class ViewHolderIngredient(private val binding : ItemIngredientBinding)
            : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(ingredient: String) {
            binding.tvIngredient.text = ingredient
        }
    }
}