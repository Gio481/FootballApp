package com.example.customview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.R
import com.example.customview.databinding.HalfTimeCustomViewBinding
import com.example.customview.types.HalfTimeType

class HalfTimeCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        HalfTimeCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    var halfTime: HalfTimeType? = null
        set(value) {
            value?.let { determineHalfTime(it) }
            field = value
        }
    var score: String? = null
        set(value) {
            binding.halfTimeScore.text = value
            field = value
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HalfTimeCustomView,
            defStyleAttr,
            0
        )
        halfTime =
            HalfTimeType.values()[typedArray.getInt(R.styleable.HalfTimeCustomView_halfTime, 0)]
        score = typedArray.getString(R.styleable.HalfTimeCustomView_halfTimeScore)
        typedArray.recycle()
    }

    private fun determineHalfTime(halfTimeType: HalfTimeType) {
        with(binding.matchHalfTextView) {
            text = when (halfTimeType) {
                HalfTimeType.FirstHalf -> context.getString(R.string.first_half_text)
                HalfTimeType.SecondHalf -> context.getString(R.string.second_half_text)
            }
        }
    }
}