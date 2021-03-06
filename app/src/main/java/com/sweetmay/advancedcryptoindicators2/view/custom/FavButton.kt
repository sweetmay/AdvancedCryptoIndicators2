package com.sweetmay.advancedcryptoindicators2.view.custom

import android.content.Context
import android.util.AttributeSet
import com.sweetmay.advancedcryptoindicators2.R

class FavButton @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    var checked = false
        set(value) {
            field = value
            if (field) {
                setImageResource(R.drawable.fav_custom_button_filled)
            } else {
                setImageResource(R.drawable.fav_custom_button_out)
            }
        }

    fun toggle(){
        checked = !checked
    }
}