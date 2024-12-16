package com.petproject.server

import com.google.gson.GsonBuilder
import com.petproject.server.api.RemoteApi
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals

@HiltAndroidTest
class RetrofitUnitTest {

    lateinit var mockWebServer: MockWebServer

    lateinit var apiService: RemoteApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(RemoteApi::class.java)
    }

    @After
    fun teardown() = mockWebServer.shutdown()

    @Test
    fun `test everything API success`() = runTest {
        val mockResponse = MockResponse()
            .setBody(EVERYTHING_RESPONSE)
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val users = apiService.everything(KEYWORD)

        assertEquals(expected = 2L, actual = users.totalResults, message = "Success")
    }

    companion object Constants {
        const val EVERYTHING_RESPONSE = "" +
                "{\n" +
                "   \"status\":\"ok\",\n" +
                "   \"totalResults\":2,\n" +
                "   \"articles\":[\n" +
                "      {\n" +
                "         \"source\":{\n" +
                "            \"id\":\"wired\",\n" +
                "            \"name\":\"Wired\"\n" +
                "         },\n" +
                "         \"author\":\"Makena Kelly, Joel Khalili\",\n" +
                "         \"title\":\"The Crypto Industry Is Helping Trump Pick SEC Chair\",\n" +
                "         \"description\":\"The president-elect's transition team is consulting with industry leaders as it vets potential replacements for outgoing chair Gary Gensler, sources tell WIRED.\",\n" +
                "         \"url\":\"https://www.wired.com/story/crypto-candidates-front-runners-sec-race/\",\n" +
                "         \"urlToImage\":\"https://media.wired.com/photos/6745db10e149b18ca8e2b8d8/191:100/w_1280,c_limit/GettyImages-93181618.jpg\",\n" +
                "         \"publishedAt\":\"2024-11-26T16:23:34Z\",\n" +
                "         \"content\":\"In July, at a bitcoin conference in Nashville, Tennessee, Trump pledged to fire Gensler if reelected, drawing perhaps the most raucous applause of the night. I will appoint an SEC chair who will buil… [+2635 chars]\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"source\":{\n" +
                "            \"id\":null,\n" +
                "            \"name\":\"Quartz India\"\n" +
                "         },\n" +
                "         \"author\":\"Vinamrata Chaturvedi\",\n" +
                "         \"title\":\"The Dow gains 200 points as Salesforce and other tech stocks rise\",\n" +
                "         \"description\":\"The Dow surged nearly 200 points on Wednesday morning, driven by gains in Salesforce and other tech stocks. Shortly after the opening bell, the Dow climbed 170 points or 0.3%. The S&P 500 added 0.3%, while the Nasdaq rose by 0.8%. Both the S&P 500 and Nasdaq …\",\n" +
                "         \"url\":\"https://qz.com/dow-salesforce-tech-stock-adp-report-rate-cuts-1851713262\",\n" +
                "         \"urlToImage\":\"https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/e5c5908bebf5c027a1d913e2e7214250.jpg\",\n" +
                "         \"publishedAt\":\"2024-12-04T15:38:00Z\",\n" +
                "         \"content\":\"In This Story\\r\\nThe Dow surged nearly 200 points on Wednesday morning, driven by gains in Salesforce and other tech stocks. Shortly after the opening bell, the Dow climbed 170 points or 0.3%. The S&am… [+1918 chars]\"\n" +
                "      }\n" +
                "   ]\n" +
                "}"

        const val KEYWORD = "bitcoin"
    }

}