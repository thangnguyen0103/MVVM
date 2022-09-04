package com.thangnguyen.demomvvm.util.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*


inline fun <T> Fragment.bind(
    source: Flow<T>,
    crossinline action: suspend (T) -> Unit
) {
    source.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .onEach { action.invoke(it) }
        .launchIn(viewLifecycleOwner.lifecycleScope)
}

fun snackbar(@StringRes message: Int, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun snackbar(message: String, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun SearchView.textChanges(): Flow<String> {
    return callbackFlow {
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                trySend(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                trySend(newText.orEmpty())
                return false
            }
        }
        setOnQueryTextListener(listener)
        awaitClose {
            setOnQueryTextListener(null)
        }
    }
}



