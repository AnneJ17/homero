package com.apolis.homero.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.apolis.homero.R
import kotlinx.android.synthetic.main.prefix_layout.view.*

class PrefixLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var title: String? = null
        set(value) {
            field = value
            text_view_title.text = field
        }
    private var amount: String? = null

    set(value) {
        field = value
        edit_text_amount.assignPrefix(field!!)
    }

    init {
        View.inflate(context, R.layout.prefix_layout, this)
        var attributes = context.obtainStyledAttributes(attrs, R.styleable.PrefixLayout)
        attributes.let {
            title = it.getString(R.styleable.PrefixLayout_title)
            amount = it.getString(R.styleable.PrefixLayout_prefix)
            it.recycle()
        }
    }
}