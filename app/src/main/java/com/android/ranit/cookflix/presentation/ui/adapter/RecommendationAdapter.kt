package com.android.ranit.cookflix.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ranit.cookflix.data.model.response.Recommendation
import com.android.ranit.cookflix.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

class RecommendationAdapter: RecyclerView.Adapter<RecommendationAdapter.ViewHolderRecommendation>() {
    private val tag = RecommendationAdapter::class.simpleName
    private val mArrayListRecommendationData = ArrayList<Recommendation>()
    var onItemClickCallback: ((Recommendation?, Int) -> Unit)? = null

    fun setData(newRecommendedList: List<Recommendation>) {
        Log.d(tag, "setData() called of size = ${newRecommendedList.size}")
        val diffUtilCallback = RecommendationDiffUtilCallback(
            mArrayListRecommendationData,
            newRecommendedList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        mArrayListRecommendationData.clear()
        mArrayListRecommendationData.addAll(newRecommendedList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecommendation {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderRecommendation(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderRecommendation, position: Int) {
        val curResult = mArrayListRecommendationData[position]
        holder.bindViews(curResult)
    }

    override fun getItemCount(): Int {
        return mArrayListRecommendationData.size
    }

    // View-Holder class
    inner class ViewHolderRecommendation(private val binding : ItemFoodBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(result: Recommendation) {
            Glide.with(binding.ivFoodThumbnail.context)
                .load(result.image)
                .into(binding.ivFoodThumbnail)

            binding.tvFoodTitle.text = result.recipe_name
            binding.tvDishOrigin.text = "Mexican"
            binding.tvDishCost.text = "2\$ for two people"

            binding.tvLevel.text = result.score

            itemView.setOnClickListener {
                onItemClickCallback?.invoke(result, mArrayListRecommendationData.indexOf(result))
            }
        }
    }
}