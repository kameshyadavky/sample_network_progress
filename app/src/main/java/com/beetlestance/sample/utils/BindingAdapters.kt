package com.beetlestance.sample.utils

import android.text.method.ScrollingMovementMethod
import androidx.core.text.parseAsHtml
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import soup.neumorphism.NeumorphFloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

@BindingAdapter("shapeType")
fun neuromorphToggleFabShape(view: NeumorphFloatingActionButton, shapeType: Int) {
    view.setShapeType(shapeType)
}

@BindingAdapter("textScrolling")
fun textViewScrolling(view: MaterialTextView, enable: Boolean) {
    if (enable) {
        view.isVerticalScrollBarEnabled = true
        view.movementMethod = ScrollingMovementMethod()
    }
}
