package com.rahul.weatherapp

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.rahullohra.myweatherapp.R
import com.rahullohra.myweatherapp.presentation.ui.activity.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherListTest  {
//    var idlingResource: WeatherIdlingResource? = null
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Rule @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    @Before
    fun initDependencies() {
//        idlingResource = IdlingResourceProvider.provideIdlingResource("MainActivity")
//        IdlingRegistry.getInstance().register(idlingResource)
    }

    @Test
    fun testListData() {

        onView(
            RecyclerViewMatcher(R.id.rv)
                .atPositionOnView(0, R.id.tv_item_title)
        )
            .check(matches(withText(CoreMatchers.endsWith("day"))))

        onView(
            RecyclerViewMatcher(R.id.rv)
                .atPositionOnView(0, R.id.tv_item_temp)
        )
            .check(matches(withText(CoreMatchers.endsWith("C"))))
    }

    @After
    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
    }


    class RecyclerViewMatcher(val recyclerViewId: Int) {

        fun atPosition(position: Int): Matcher<View> {
            return atPositionOnView(position, -1)
        }

        fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                internal var resources: Resources? = null
                internal var childView: View? = null
                override fun describeTo(description: Description) {
                    var idDescription = Integer.toString(recyclerViewId)
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources!!.getResourceName(recyclerViewId)
                        } catch (var4: Resources.NotFoundException) {
                            idDescription = String.format(
                                "%s (resource name not found)",
                                *arrayOf<Any>(Integer.valueOf(recyclerViewId))
                            )
                        }

                    }
                    description.appendText("with id: $idDescription")
                }

                override fun matchesSafely(view: View): Boolean {
                    this.resources = view.resources
                    if (childView == null) {
                        val recyclerView = view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                        if (recyclerView != null && recyclerView.id == recyclerViewId) {
                            childView = recyclerView.findViewHolderForAdapterPosition(position)!!.itemView
                        } else {
                            return false
                        }
                    }
                    if (targetViewId == -1) {
                        return view === childView
                    } else {
                        val targetView = childView!!.findViewById<View>(targetViewId)
                        return view === targetView
                    }
                }
            }
        }
    }


}