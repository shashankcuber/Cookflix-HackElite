package com.android.ranit.cookflix.presentation.ui.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ranit.cookflix.R
import com.android.ranit.cookflix.databinding.ItemFoodBinding
import com.android.ranit.cookflix.data.model.response.Result
import com.bumptech.glide.Glide

class FoodItemsAdapter: RecyclerView.Adapter<FoodItemsAdapter.ViewHolderFoodItem>() {
    private val tag = FoodItemsAdapter::class.simpleName
    private val mArrayListFoodItem = ArrayList<Result>()
    var onItemClickCallback: ((Result?, Int) -> Unit)? = null

    fun setData(newFoodItemList: List<Result>) {
        Log.d(tag, "setData() called of size = ${newFoodItemList.size}")
        val diffUtilCallback = FoodItemDiffUtilCallback(
            mArrayListFoodItem,
            newFoodItemList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        mArrayListFoodItem.clear()
        mArrayListFoodItem.addAll(newFoodItemList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFoodItem {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderFoodItem(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderFoodItem, position: Int) {
        val curFoodItem = mArrayListFoodItem[position]
        holder.bindViews(curFoodItem)
    }

    override fun getItemCount(): Int {
        return mArrayListFoodItem.size
    }

    // View-Holder class
    inner class ViewHolderFoodItem(private val binding : ItemFoodBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(result: Result) {
            val itemSize = result.ingredient_list.size

            Glide.with(binding.ivFoodThumbnail.context)
                .load(result.image)
                .into(binding.ivFoodThumbnail)

            binding.tvFoodTitle.text = result.title
            binding.tvDishOrigin.text = "Mexican"
            binding.tvDishCost.text = "2\$ for two people"

            binding.tvLevel.text = computeCookingLevel(itemSize, binding)
            binding.tvIngredients.text = "$itemSize ingredients needed"

            itemView.setOnClickListener {
                onItemClickCallback?.invoke(result, mArrayListFoodItem.indexOf(result))
            }
        }
    }

    private fun computeCookingLevel(ingredientListSize: Int, binding: ItemFoodBinding): String {
        var level = ""
        if (ingredientListSize >= 15) {
            level = "Hard"
            binding.tvLevel.background = ResourcesCompat.getDrawable(
                binding.tvLevel.context.resources,
                R.drawable.bg_difficulty_hard,
                null
            )
        } else if (ingredientListSize in 9..14) {
            level = "Medium"
            binding.tvLevel.background = ResourcesCompat.getDrawable(
                binding.tvLevel.context.resources,
                R.drawable.bg_difficulty_medium,
                null
            )
        } else {
            level = "Easy"
            binding.tvLevel.background = ResourcesCompat.getDrawable(
                binding.tvLevel.context.resources,
                R.drawable.bg_difficulty_easy,
                null
            )
        }
        return level
    }
}