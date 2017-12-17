package domitunja.appsolution.co.domitunja

import android.widget.TabHost
//import android.view.GestureDetector
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation



class AnimatedTabHostListener
/**
 * Constructor that takes the TabHost as a parameter and sets previousView to the currentView at instantiation
 *
 * @param tabHost
 */(tabHost: TabHost) : TabHost.OnTabChangeListener {
    private val animationTime = 240
    private var tabHost: TabHost? = tabHost
    private var previousView: View? = null
    private var currentView: View? = null
    //private var gestureDetector: GestureDetector? = null
    private var currentTab: Int = 0

    /**
     * When tabs change we fetch the current view that we are animating to and animate it and the previous view in the
     * appropriate directions.
     */
    override fun onTabChanged(tabId: String) {

        currentView = tabHost!!.currentView
        if (tabHost!!.currentTab > currentTab) {
            previousView!!.animation = outToLeftAnimation()
            currentView!!.animation = inFromRightAnimation()
        } else {
            previousView!!.animation = outToRightAnimation()
            currentView!!.animation = inFromLeftAnimation()
        }
        previousView = currentView
        currentTab = tabHost!!.currentTab

    }

    /**
     * Custom animation that animates in from right
     *
     * @return Animation the Animation object
     */
    private fun inFromRightAnimation(): Animation {
        val inFromRight = TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f)
        return setProperties(inFromRight)
    }

    /**
     * Custom animation that animates out to the right
     *
     * @return Animation the Animation object
     */
    private fun outToRightAnimation(): Animation {
        val outToRight = TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f)
        return setProperties(outToRight)
    }

    /**
     * Custom animation that animates in from left
     *
     * @return Animation the Animation object
     */
    private fun inFromLeftAnimation(): Animation {
        val inFromLeft = TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f)
        return setProperties(inFromLeft)
    }

    /**
     * Custom animation that animates out to the left
     *
     * @return Animation the Animation object
     */
    private fun outToLeftAnimation(): Animation {
        val outtoLeft = TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f)
        return setProperties(outtoLeft)
    }

    /**
     * Helper method that sets some common properties
     * @param animation the animation to give common properties
     * @return the animation with common properties
     */
    private fun setProperties(animation: Animation): Animation {
        animation.duration = animationTime.toLong()
        animation.interpolator = AccelerateInterpolator()
        return animation
    }

    init {
        this.previousView = tabHost.currentView
    }
}