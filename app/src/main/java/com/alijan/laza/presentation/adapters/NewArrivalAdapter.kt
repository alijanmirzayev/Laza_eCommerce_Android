package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.R
import com.alijan.laza.common.toWishlistLocalDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO
import com.alijan.laza.databinding.ItemNewArrivalBinding

class NewArrivalAdapter : RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {

    private val productList = ArrayList<ProductDTO>()
    private val wishList = ArrayList<WishlistLocalDTO>()

    lateinit var onClick: (id: String) -> Unit
    lateinit var onClickAddWishlist: (item: ProductDTO) -> Unit
    lateinit var onClickDeleteWishlist: (item: ProductDTO) -> Unit

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

        val isExist = wishList.find { it.productId == currentItem.id }
        if (isExist != null) {
            holder.itemNewArrivalBinding.imageViewItemNewArrivalWishlist.setImageResource(R.drawable.icon_wishlist_red)
        } else holder.itemNewArrivalBinding.imageViewItemNewArrivalWishlist.setImageResource(R.drawable.icon_wishlist)

        holder.itemNewArrivalBinding.clItemNewArrival.setOnClickListener {
            onClick(currentItem.id!!)
        }
        holder.itemNewArrivalBinding.imageViewItemNewArrivalWishlist.setOnClickListener {
            if (isExist != null) {
                holder.itemNewArrivalBinding.imageViewItemNewArrivalWishlist.setImageResource(R.drawable.icon_wishlist)
                onClickDeleteWishlist(currentItem)
                wishList.remove(isExist)
            } else {
                holder.itemNewArrivalBinding.imageViewItemNewArrivalWishlist.setImageResource(R.drawable.icon_wishlist_red)
                onClickAddWishlist(currentItem)
                wishList.add(currentItem.toWishlistLocalDTO())
            }
        }

    }

    fun updateList(newList: List<ProductDTO>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateWishlistList(newList: List<WishlistLocalDTO>) {
        wishList.clear()
        wishList.addAll(newList)
        notifyDataSetChanged()
    }

}