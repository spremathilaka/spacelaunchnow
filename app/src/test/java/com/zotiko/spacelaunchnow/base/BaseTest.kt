package com.zotiko.spacelaunchnow.base

import com.zotiko.spacelaunchnow.utils.TestUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
    }

    // MOCK SERVER
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    open fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) {
        val response = TestUtils.readTestResourceFile(fileName)
        return mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(response)
        )
    }
}