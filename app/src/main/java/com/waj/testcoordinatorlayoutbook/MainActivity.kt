package com.waj.testcoordinatorlayoutbook

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTbToolbar!!.title = ""

        // 向上或向下滑动AppBar就会触发监听
        mAblAppBar!!.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()
            handleAlphaOnTitle(percentage)
            handleToolbarTitleVisibility(percentage)
        })

        initParallaxValues() // 设置自动滑动（视差）效果
    }

    // 设置自动滑动（视差）效果
    private fun initParallaxValues() {
        val petDetailsLp = mIvPlaceholder!!.layoutParams as CollapsingToolbarLayout.LayoutParams

        val petBackgroundLp = mFlTitleContainer!!.layoutParams as CollapsingToolbarLayout.LayoutParams

        petDetailsLp.parallaxMultiplier = 0.9f
        petBackgroundLp.parallaxMultiplier = 0.3f

        mIvPlaceholder!!.layoutParams = petDetailsLp
        mFlTitleContainer!!.layoutParams = petBackgroundLp
    }

    // 处理ToolBar的显示
    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle!!, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle!!, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    // 控制Title的显示
    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer!!, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer!!, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
                mIsTheTitleContainerVisible = true
            }
        }
    }

    companion object {

        // 控制ToolBar的变量
        private val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
        private val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f

        private val ALPHA_ANIMATIONS_DURATION = 200

        // 设置渐变的动画
        fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
            val alphaAnimation = if (visibility == View.VISIBLE)
                AlphaAnimation(0f, 1f)
            else
                AlphaAnimation(1f, 0f)

            alphaAnimation.duration = duration
            alphaAnimation.fillAfter = true
            v.startAnimation(alphaAnimation)
        }
    }
}
