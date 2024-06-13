package com.alijan.laza.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setImageFromUrl")
fun setImageFromUrl(imageView: ImageView, url: String?){
    url?.let {
        imageView.setImageFromUrl(it)
    }
}