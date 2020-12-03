package com.sweetmay.advancedcryptoindicators2.view.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable

class FavButton @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr), Checkable{

    private var boolean = false

    override fun setChecked(checked: Boolean) {
        boolean = checked
    }

    override fun isChecked(): Boolean {
        return boolean
    }

    override fun toggle() {
        boolean = if(boolean){
            !boolean
        }else !boolean
    }
}