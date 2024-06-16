package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.databinding.ItemNewArrivalBinding

class NewArrivalAdapter : RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {

    private val productList = ArrayList<ProductDTO>()
    lateinit var onClick: (id: String) -> Unit

    inner class NewArrivalViewHolder(val itemNewArrivalBinding: ItemNewArrivalBinding) :
        RecyclerView.ViewHolder(itemNewArrivalBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = ItemNewArrivalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewArrivalViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemNewArrivalBinding.item = currentItem
        holder.itemNewArrivalBinding.clItemNewArrival.setOnClickListener {
            onClick(currentItem.id!!)
        }
    }

    fun updateList(newList: List<ProductDTO>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

}