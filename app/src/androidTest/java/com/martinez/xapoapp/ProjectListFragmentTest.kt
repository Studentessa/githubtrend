package com.martinez.xapoapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.martinez.xapoapp.base.util.EspressoIdlingResource
import com.martinez.xapoapp.presentation.MainActivity
import com.martinez.xapoapp.presentation.paymentList.adapter.ProjectRecyclerViewAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ProjectListFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun displayRecyclerView() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView2)).
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        // Click list item
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView2))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ProjectRecyclerViewAdapter.ProjectViewHolder>(1, ViewActions.click()))

        // Confirm nav to DetailFragment
        Espresso.onView(ViewMatchers.withId(R.id.imageViewAvatar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}