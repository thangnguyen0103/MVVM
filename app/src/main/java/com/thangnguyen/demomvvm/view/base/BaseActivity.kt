package com.thangnguyen.demomvvm.view.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.thangnguyen.demomvvm.util.ext.gone
import com.thangnguyen.demomvvm.util.ext.snackbar
import com.thangnguyen.demomvvm.util.ext.visible


abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var binding: ViewDataBinding

    abstract fun layoutRsc(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRsc())
    }

    fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) = snackbar(errorMessage ?: "", rootView)

    fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar.gone()
}