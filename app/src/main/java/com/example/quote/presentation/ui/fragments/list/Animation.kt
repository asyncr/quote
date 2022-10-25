package com.example.quote.presentation.ui.fragments.list

import android.animation.ValueAnimator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Animation {
    companion object {
        private var flag = true

        fun animationButton(rv: RecyclerView, tv: TextView, animator: ValueAnimator? = null) {
            var animator = animator
            rv.also {
                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (animator == null) {
                            animator = createAnimation(tv)
                        }
                        when {
                            dy > 0 && flag -> {
                                animator?.start()
                                flag = !flag
                            }
                            dy < 0 && !flag -> {
                                animator?.reverse()
                                flag = !flag
                            }
                        }
                    }
                })
            }
        }

        fun createAnimation(tvAddQuote: TextView): ValueAnimator {
            val initSize = tvAddQuote.measuredWidth
            val animator = ValueAnimator.ofInt(initSize, 0)
            animator.duration = 250
            animator.addUpdateListener {
                val value = it.animatedValue as Int
                tvAddQuote.layoutParams.width = value
                tvAddQuote.requestLayout()
            }
            return animator
        }
    }
}