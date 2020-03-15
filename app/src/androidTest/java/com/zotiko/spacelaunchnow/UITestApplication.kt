package com.zotiko.spacelaunchnow

import com.zotiko.spacelaunchnow.di.component.DaggerUITestAppComponent

class UITestApplication : SpaceLaunchApplication() {

    //NOTE: Remember to execute the tests or run assembleAndroidTest to actually see the dependency class
    // ./gradlew assembleAndroidTest
    override fun initDaggerAppComponent() {
        DaggerUITestAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}



