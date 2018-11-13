package com.waj.testcoordinatorlayoutbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mIsTheTitleContainerVisible:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_tb_toolbar.title = ""

//        main_tab_app_bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            val maxScroll = appBarLayout.totalScrollRange
//            val percentage = Math.abs(verticalOffset) / maxScroll
//            handleAlphaOnTitle(percentage)
//            handleTopbarTitleVisibility(percentage)
//        }
//
//        initParallaxValue()
    }

//    private fun handleAlphaOnTitle(percentage: Float) {
//        if (percentage >= 0.9){
//            if (mIsTheTitleContainerVisible){
//                startAlphaAnimation()
//            }
//        }else{
//
//        }
//    }
}
