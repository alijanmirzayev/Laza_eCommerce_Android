package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.databinding.ItemDetailSizeBinding

class SizeAdapter : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private val sizeList = ArrayList<String>()
    private var selectedSize: String? = null
    private var lastSelectedPosition = -1
    lateinit var onClickSelectSize: (size: String) -> Unit

    inner class SizeViewHolder(val itemDetailSizeBinding: ItemDetailSizeBinding) :
        RecyclerView.ViewHolder(itemDetailSizeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = ItemDetailSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(view)
    }

    override fun getItemCount(): Int = sizeList.size

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val currentItem = sizeList[position]
        holder.itemDetailSizeBinding.item = currentItem
        holder.itemDetailSizeBinding.selectedItem = selectedSize

        holder.itemDetailSizeBinding.clItemDetailSize.setOnClickListener {
            onClickSelectSize(currentItem)

            selectedSize = currentItem
            notifyItemChanged(position)

            notifyItemChanged(lastSelectedPosition)
            lastSelectedPosition = position
        }
    }

    fun updateList(newList: List<String>) {
        sizeList.clear()
        sizeList.addAll(newList)
        notifyDataSetChanged()
    }
}