package com.vishalgaur.testinguserdata

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class DetailFragmentTest {
    private lateinit var detailScenario: FragmentScenario<DetailFragment>
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        detailScenario =
            launchFragmentInContainer(themeResId = R.style.Theme_TestingUserData)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
            detailScenario.onFragment {
                Navigation.setViewNavController(it.requireView(), navController)
            }
        }
    }

    @Test
    fun infoIsSet() {
        detailScenario.onFragment {
            val name = "Vishal"
            val email = "   vishal@mail.com "
            val mob = "  7056897878"
            val bio = "iweurhfgw roirhf \neogiuehri  oisfe."

            it.viewModel.submitData(name, email, mob, bio)
        }
        detailScenario.recreate()

        onView(withId(R.id.detail_name)).check(matches(withText("Vishal")))
        onView(withId(R.id.detail_email)).check(matches(withText("vishal@mail.com")))
        onView(withId(R.id.detail_mobile)).check(matches(withText("7056897878")))
        onView(withId(R.id.detail_bio)).check(matches(withText("iweurhfgw roirhf \neogiuehri  oisfe.")))
    }

    @Test
    fun onBackPressed_navigateToInputFragment() {
        onView(withId(R.id.detail_go_back_btn)).perform(scrollTo(), click())
        assertEquals(navController.currentDestination?.id, R.id.inputFragment)
    }
}