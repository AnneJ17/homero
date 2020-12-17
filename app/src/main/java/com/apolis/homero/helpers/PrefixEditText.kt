package com.apolis.homero.helpers

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.apolis.homero.R

class PrefixEditText(context: Context, attributes: AttributeSet) :
    AppCompatEditText(context, attributes) {

    companion object {
        private const val DEFAULT_PADDING = -1f
    }

    private var defaultLeftPadding =
        DEFAULT_PADDING
    private var prefix = ""

        // Stores the value of the property for the given object in this mutable map.
        set(value) {
            field = value
            calculatePrefix()
            requestLayout() // to be called when size is changed
            invalidate() // to be called if only visual change happens
        }

    init {
        context.obtainStyledAttributes(attributes, R.styleable.PrefixEditText).let {

            prefix = it.getString(R.styleable.PrefixEditText_prefix).orEmpty()
            it.recycle()
        }
    }

    // Setter for assigning prefix within any activity or fragments programmatically
    fun assignPrefix(prefix: String) {
        this.prefix = prefix
    }

    // Tells the android how big I want the custom view to be
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        calculatePrefix()
    }

    private fun calculatePrefix() {
        // If padding = -1f, then it's a new edit text wit no prefix set yet
        if (defaultLeftPadding == DEFAULT_PADDING) {
            val widths = FloatArray(prefix.length)
            // return advance widths of the characters in the the string
            // prefix - text to measure, widths (float: array) to receive the widths of the chars
            paint.getTextWidths(prefix, widths)
            var textWidths = 0f
            for (w in widths) {
                textWidths += w
            }
            //returns the left padding of the view, plus space for the left Drawable if any.
            defaultLeftPadding = compoundPaddingLeft.toFloat()
            var paddingLeft = (textWidths + defaultLeftPadding).toInt()
            setPadding(
                paddingLeft,
                paddingTop,
                paddingRight,
                paddingBottom
            ) // padding bottom - b/w text and edit text underline
        }
    }

    // for creating custom ui
    // canvas object - view use to draw itself
    // canvas handle what to draw, paint handles how to draw
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(prefix, defaultLeftPadding, getLineBounds(0, null).toFloat(), paint)
    }
}