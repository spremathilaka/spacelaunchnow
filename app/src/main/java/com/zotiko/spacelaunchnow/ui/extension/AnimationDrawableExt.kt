package com.zotiko.spacelaunchnow.ui.extension

import android.graphics.drawable.AnimationDrawable

internal fun AnimationDrawable.startWithFade() {
    this.setEnterFadeDuration(1000)
    this.setExitFadeDuration(1000)
    this.start()
}