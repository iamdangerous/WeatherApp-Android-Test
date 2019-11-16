package com.rahullohra.myweatherapp

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.rahullohra.myweatherapp.data.di.modules.AppModule
import com.rahullohra.myweatherapp.di.components.DaggerTestComponent
import com.rahullohra.myweatherapp.domain.usecase.GetWeatherUseCase
import com.rahullohra.myweatherapp.domain.usecase.LocationUseCase
import com.rahullohra.myweatherapp.presentation.ui.activity.MainActivity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import org.hamcrest.CoreMatchers.endsWith
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class WeatherAppUiTest {

    @Inject
    lateinit var  getWeatherUseCase: GetWeatherUseCase
    @Inject
    lateinit var  locationUseCase: LocationUseCase
    //    var idlingResource: WeatherIdlingResource? = null
    val context = ApplicationProvider.getApplicationContext<WeatherApp>()
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    @Before
    fun initDependencies() {
        MockKAnnotations.init(this)
//        idlingResource = IdlingResourceProvider.provideIdlingResource("MainActivity")
//        IdlingRegistry.getInstance().register(idlingResource)
    }


////    @Test
//    fun onSuccessVisibility() {
//        activityRule.launchActivity(null)
//
//        val degree = "\u00B0"
//        onView(withId(R.id.tv_temp)).check(matches(withText(endsWith(degree))))
//        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.ll_container)).check(matches(isDisplayed()))
//        onView(withId(R.id.loading_image)).check(matches(withEffectiveVisibility(Visibility.GONE)))
//    }

//    @Test
//    fun onFailureVisibility() {
//        enableAirplaneMode()
//        onView(withId(R.id.error_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//    }

    @Test
    fun onFailureVisibility() {
        // given
        val testComponent =
            DaggerTestComponent.builder()
                .appModule(AppModule(context))
                .build()
        (context as WeatherApp).appComponent = testComponent
        testComponent.inject(this)

        coEvery { locationUseCase.processAddress(any(),any()) } throws Exception()

        //when
        activityRule.launchActivity(null)

        //then
        onView(withId(R.id.error_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.loading_image)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

//    @Test
//    fun onAirplaneRetryVisibility() {
//        enableAirplaneMode()
//
//        onView(withId(R.id.btn_retry))
//            .check(matches(isDisplayed()))
//            .perform(click())
//
//        onView(withId(R.id.error_view)).check(matches(isDisplayed()))
//
//    }

    @After
    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}