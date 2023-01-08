package com.android.ranit.cookflix.presentation.ui.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.android.ranit.cookflix.data.model.response.Result

class FoodItemDiffUtilCallback(
    private val oldList : List<Result>,
    private val newList : List<Result>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int  = oldList.size

    override fun getNewListSize(): Int  = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}