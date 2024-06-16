package com.alijan.laza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.laza.databinding.ItemDetailImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val imageList = ArrayList<String>()
    lateinit var onClickImage: (image: String) -> Unit

    inner class ImageViewHolder(val itemDetailImageBinding: ItemDetailImageBinding) :
        RecyclerView.ViewHolder(itemDetailImageBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.itemDetailImageBinding.item = currentItem
        holder.itemDetailImageBinding.clItemDetailImage.setOnClickListener {
            onClickImage(currentItem)
        }
    }

    fun updateList(newList: List<String>){
        imageList.clear()
        imageList.addAll(newList)
        notifyDataSetChanged()
    }

}