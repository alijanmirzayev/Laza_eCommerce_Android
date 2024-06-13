package com.alijan.laza.common.utils

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import com.alijan.laza.databinding.LoadingBinding
import com.bumptech.glide.Glide
import com.shashank.sony.fancytoastlib.FancyToast

fun Context.showFancyToast(message: String, type: Int) {
    val toast = FancyToast.makeText(
        this,
        message,
        FancyToast.LENGTH_LONG,
        type,
        false
    )
    toast.setGravity(Gravity.TOP, 0, 150)
    toast.show()
}

fun Context.loadingDialog(): Dialog {
    val dialog = Dialog(this)
    val dialogBinding = LoadingBinding.inflate(LayoutInflater.from(this))
    dialog.setContentView(dialogBinding.root)
    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    return dialog
}

fun ImageView.setImageFromUrl(url: String?) {
    Glide
        .with(this)
        .load(url)
        .fitCenter()
        .into(this)
}