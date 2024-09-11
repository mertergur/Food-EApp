package com.example.foodeapp.util

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R

fun TextView.coloredString(textView:TextView,selectedColor: Int, coloredPart: Int, context: Context ) {
    val color = ContextCompat.getColor(context,selectedColor)
    val colorStart = textView.text.length - coloredPart
    val colorEnd = textView.text.length
    val spannableString = SpannableString(textView.text)
    spannableString.setSpan(
        ForegroundColorSpan(color),
        colorStart,
        colorEnd,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = spannableString
}

fun Navigation.goTo(it: View, id: Int){
    findNavController(it).navigate(id)
}

