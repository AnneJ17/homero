package com.apolis.homero.helpers

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.apolis.homero.R
import kotlinx.android.synthetic.main.prefix_layout.view.*

class PrefixLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var title: String = ""
        set(value) {
            field = value
            text_view_title.text = field
        }

    init {
//        orientation = VERTICAL
        inflate(context, R.layout.prefix_layout, this)

        //R.styleable has to be the same name as it is in attrs.xml
        context.obtainStyledAttributes(attrs, R.styleable.PrefixLayout).let {

            title = it.getString(R.styleable.PrefixLayout_title).orEmpty()

            it.recycle()
        }
    }

    /** Important:
     * These interfaces act as keys, you need to make sure your CustomViews extend them accordingly.
     * This will make it easier to customise these views when we add them to this Layout.
     */
    interface Spinner
    interface RadioButton
}