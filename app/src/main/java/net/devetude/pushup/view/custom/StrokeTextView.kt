package net.devetude.pushup.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatTextView
import net.devetude.pushup.R

class StrokeTextView : AppCompatTextView {
    @FloatRange(from = 0.0)
    private var strokeWidth = 0.0f

    @ColorInt
    private var strokeColor: Int = 0

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        obtainStyledAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        obtainStyledAttrs(context, attrs)
    }

    constructor(context: Context) : super(context) {
        obtainStyledAttrs(context, attrs = null)
    }

    private fun obtainStyledAttrs(context: Context, attrs: AttributeSet?) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView)
        strokeWidth = styledAttrs.getFloat(
            R.styleable.StrokeTextView_stroke_width,
            0.0f /* defValue */
        )
        strokeColor = styledAttrs.getColor(
            R.styleable.StrokeTextView_stroke_color,
            0 /* defValue */
        )
        styledAttrs.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        val textColor = textColors

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        setTextColor(strokeColor)
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        setTextColor(textColor)
        super.onDraw(canvas)
    }
}
