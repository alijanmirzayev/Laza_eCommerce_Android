package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.databinding.ItemBasketBinding

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val basketList = ArrayList<BasketLocalDTO>()
    lateinit var onClickCalculation: (basketList: List<BasketLocalDTO>) -> Unit
    lateinit var onClickDelete: (deletedItem: BasketLocalDTO) -> Unit
    lateinit var onClickDecrement: (decrementItem: BasketLocalDTO) -> Unit
    lateinit var onClickIncrement: (incrementItem: BasketLocalDTO) -> Unit

    inner class BasketViewHolder(val itemBasketBinding: ItemBasketBinding) :
        RecyclerView.ViewHolder(itemBasketBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(view)
    }

    override fun getItemCount(): Int = basketList.size

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val currentItem = basketList[position]
        holder.itemBasketBinding.item = currentItem
        holder.itemBasketBinding.apply {
            imageViewItemBasketDelete.setOnClickListener {
                onClickDelete(currentItem)
                basketList.removeAt(position)
                notifyItemRemoved(position)
                onClickCalculation(basketList)
            }
            imageViewItemBasketUp.setOnClickListener {
                onClickIncrement(currentItem)
                currentItem.productCount?.let {
                    currentItem.productCount = it.plus(1)
                    notifyItemChanged(position)
                }
                onClickCalculation(basketList)
            }
            imageViewItemBasketDown.setOnClickListener {
                onClickDecrement(currentItem)
                currentItem.productCount?.let {
                    if (it > 1) {
                        currentItem.productCount = it.minus(1)
                        notifyItemChanged(position)
                    }
                }
                onClickCalculation(basketList)
            }
        }
    }

    fun updateList(newList: List<BasketLocalDTO>) {
        basketList.clear()
        basketList.addAll(newList)
        onClickCalculation(newList)
        notifyDataSetChanged()
    }

}