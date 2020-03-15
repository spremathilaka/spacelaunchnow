package com.zotiko.spacelaunchnow.ui.extension

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zotiko.spacelaunchnow.R

fun ImageView.load(imageUrl: String) {
    if (imageUrl.isBlank()) {
        this.setImageDrawable(this.context.getDrawable(R.drawable.im_rocket_one)!!)
    } else {
        Picasso.get()
            .load(imageUrl)
            .error(this.context.getDrawable(R.drawable.im_rocket_one)!!)
            .into(this)
    }
}

fun ImageView.load(imageUrl: String?, callback: ImageLoadingCallback) {
    Picasso.get()
        .load(imageUrl)
        .into(this, object : Callback {
            override fun onSuccess() = callback.onLoadingSuccess()

            override fun onError(e: Exception?) = callback.onError(e)
        })
}

interface ImageLoadingCallback {
    fun onLoadingSuccess()
    fun onError(e: Exception?)
}
