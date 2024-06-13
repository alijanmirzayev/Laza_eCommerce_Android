package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.data.source.remote.dto.BrandDTO
import com.alijan.laza.databinding.ItemBrandBinding

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private val brandList = ArrayList<BrandDTO>()

    inner class BrandViewHolder(val itemBrandBinding: ItemBrandBinding) :
        RecyclerView.ViewHolder(itemBrandBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(view)
    }

    override fun getItemCount(): Int = brandList.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val currentItem = brandList[position]
        holder.itemBrandBinding.item = currentItem
    }

    fun updateList(newList: List<BrandDTO>){
        brandList.clear()
        brandList.addAll(newList)
        notifyDataSetChanged()
    }

}