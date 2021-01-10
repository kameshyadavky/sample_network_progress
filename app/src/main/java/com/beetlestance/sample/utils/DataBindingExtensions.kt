package com.beetlestance.sample.utils

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <T : ViewDataBinding> Fragment.bindWithLifecycleOwner(
    bind: T.() -> Unit = {}
): T {
    val binding: T = checkNotNull(DataBindingUtil.bind(requireView()))
    binding.lifecycleOwner = viewLifecycleOwner
    binding.bind()
    return binding
}


inline fun <T : ViewDataBinding> FragmentActivity.bindWithLifecycleOwner(
    bind: T.() -> Unit = {}
): T {
    val view = checkNotNull(findViewById<ViewGroup>(R.id.content).getChildAt(0))
    val binding: T = checkNotNull(DataBindingUtil.bind(view))
    binding.lifecycleOwner = this
    binding.bind()
    return binding
}

inline fun <T : ViewDataBinding> bindWithLayout(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
    attachToRoot: Boolean = false,
    bind: (T.() -> Unit) = {}
): T {
    val inflater: LayoutInflater = LayoutInflater.from(parent.context)
    val binding: T = DataBindingUtil.inflate(inflater, layoutId, parent, attachToRoot)
    binding.bind()
    return binding
}