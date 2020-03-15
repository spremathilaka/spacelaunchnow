package com.zotiko.spacelaunchnow.ui.util.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.zotiko.spacelaunchnow.ui.extension.load

object DatabindingUtils {

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun setImageSrc(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl)
    }

    @JvmStatic
    @BindingAdapter("backgroundDrawable")
    fun setImageSrc(view: View, resId: Int) {
        view.setBackgroundResource(resId)
    }

    @JvmStatic
    @BindingAdapter("sourceDrawable")
    fun setImageSrcRes(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("show")
    fun showOrHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("enabled")
    fun enableOrDisabled(view: View, enabled: Boolean) {
        view.isEnabled = enabled
    }
}
