package com.zotiko.spacelaunchnow.ui.main

import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.UITestApplication
import com.zotiko.spacelaunchnow.di.component.DaggerUITestAppComponent
import com.zotiko.spacelaunchnow.util.CustomMatchers.Companion.withItemCount
import com.zotiko.spacelaunchnow.utils.TestUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var mockWebServer: MockWebServer

    private lateinit var app: UITestApplication

    @Before
    @Throws(Exception::class)
    fun setup() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        app = instrumentation.targetContext.applicationContext as UITestApplication

        val appInjector = DaggerUITestAppComponent.builder()
            .application(app)
            .build()
        appInjector.inject(app)

        mockWebServer = appInjector.getMockWebServer()
    }

    @Test
    fun shouldShowResultWhenApiSuccess() {
        launchActivity()
        this.mockHttpResponse("json/getLaunchList_whenSuccess.json", HttpURLConnection.HTTP_OK)
        /*onView(withId(R.id.pageLoadingIndicator)).check(
            ViewAssertions.matches(
                not(ViewMatchers.isDisplayed())
            )
        )*/
        onView(withId(R.id.launchEventRecyclerView))
            .check(ViewAssertions.matches(withItemCount(3)))
        onView(withId(R.id.launchEventRecyclerView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun shouldShowSnackBarWhenNoInternet() {
        launchActivity()
        this.mockHttpResponse(
            "json/error.json",
            HttpURLConnection.HTTP_INTERNAL_ERROR
        )
        onView(withId(R.id.launchEventRecyclerView)).check(
            ViewAssertions.matches(
                not(ViewMatchers.isDisplayed())
            )
        )
        SystemClock.sleep(1000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withText(R.string.no_internet_error_msg)))
    }

    @Test
    fun shouldShowSnackBarWhenAPIErrorResponse() {
        launchActivity()
        this.mockHttpResponse(
            "json/error.json",
            HttpURLConnection.HTTP_INTERNAL_ERROR
        )
        onView(withId(R.id.launchEventRecyclerView)).check(
            ViewAssertions.matches(
                not(ViewMatchers.isDisplayed())
            )
        )
        SystemClock.sleep(1000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(withText(R.string.generic_server_error)))
    }

    private fun mockHttpResponse(fileName: String, responseCode: Int) {
        val response = TestUtils.readTestResourceFile(fileName)
        return mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(response)
        )
    }

    private fun launchActivity() {
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, MainActivity::class.java
        )
        activityRule.launchActivity(intent)
        // ActivityScenario.launch(MainActivity::class.java)
    }
}