package com.zotiko.spacelaunchnow.ui.upcominglaunches.detail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO
import com.zotiko.spacelaunchnow.model.LaunchEvent
import com.zotiko.spacelaunchnow.utils.TestUtils
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @Test
    fun testDetailFragment() {
        // The "fragmentArgs" and "factory" arguments are optional.
        val fragmentArgs = Bundle().apply {
            putParcelable("selectedData", getDummyUpComingLaunchEvent())
        }

        launchFragmentInContainer<DetailFragment>(fragmentArgs)

        onView(withId(R.id.lbl_launch_status)).check(matches(withText("Launch Date Unconfirmed")))
    }

    private fun getDummyUpComingLaunchEvent(): LaunchEventDTO {
        return TestUtils.loadData(
            "json/launch_event.json",
            LaunchEvent::class.java
        )!!.toDTO()
    }
}