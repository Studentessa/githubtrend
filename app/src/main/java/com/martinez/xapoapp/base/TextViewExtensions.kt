package com.martinez.xapoapp.base

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.widget.TextView

fun TextView.setTextViewDrawableColor(color: String) {
    for (drawable in compoundDrawablesRelative) {
        if (drawable != null) {
            try {
                drawable.colorFilter =
                    PorterDuffColorFilter(
                        Color.parseColor(color),
                        PorterDuff.Mode.SRC_IN
                    )
            }catch (e:IllegalArgumentException){
               Log.d("Color", "IllegalArgumentException")
            }

        }
    }
}