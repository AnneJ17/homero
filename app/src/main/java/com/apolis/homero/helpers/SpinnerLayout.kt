package com.apolis.homero.helpers

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.apolis.homero.R

class SpinnerLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.spinner_layout, this)
        var attrs = context.obtainStyledAttributes(attrs, R.styleable.SpinnerLayout)
        attrs.let {
            var title = it.getString(R.styleable.SpinnerLayout_title)
            var selectId = it.getInt(R.styleable.SpinnerLayout_selectId, -1)
        }
    }
}