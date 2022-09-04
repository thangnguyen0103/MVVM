package com.thangnguyen.demomvvm.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibleGone(view: View, isVisible: Boolean?) {
        view.visibility = if (isVisible != false) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}