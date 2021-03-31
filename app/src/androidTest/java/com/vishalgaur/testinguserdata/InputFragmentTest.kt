package com.vishalgaur.testinguserdata

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputFragmentTest {

    private lateinit var inputScenario: FragmentScenario<InputFragment>
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        inputScenario =
            launchFragmentInContainer(themeResId = R.style.Theme_TestingUserData)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
            inputScenario.onFragment {
                Navigation.setViewNavController(it.requireView(), navController)
            }
        }


    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.vishalgaur.testinguserdata", appContext.packageName)
    }

    @Test
    fun userCanEnterName() {
        onView(withId(R.id.input_name_edit_text))
            .perform(typeText("Vishal Gaur"))
    }

    @Test
    fun userCanEnterEmail() {
        onView(withId(R.id.input_email_edit_text))
            .perform(typeText("vishal123456@somemail.com"))
    }

    @Test
    fun userCanEnterMobile() {
        onView(withId(R.id.input_mobile_edit_text))
            .perform(typeText("8989895656"))
    }

    @Test
    fun userCanEnterBio() {
        onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random bio with multiple      spaces."))
    }

    @Test
    fun userCanEnterBioWithMultipleLines() {
        onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random bio with enter \nsome other text.\nsome other text again!"))
    }

    @Test
    fun onSubmit_incompleteForm_showError() {
        onView(withId(R.id.input_submit_btn)).perform(click())
        onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onSubmit_partiallyFilledForm_showError() {
        onView(withId(R.id.input_mobile_edit_text))
            .perform(typeText("54687954"))
        onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random text."))

        onView(withId(R.id.input_submit_btn)).perform(scrollTo(), click())
        onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onSubmit_completeAndValidForm_noErrorNavigateToDetailScreen() {
        onView(withId(R.id.input_name_edit_text))
            .perform(typeText("Vishal Gaur"))
        onView(withId(R.id.input_mobile_edit_text))
            .perform(typeText("8468778954"))
        onView(withId(R.id.input_email_edit_text))
            .perform(typeText("vishal123456@somemail.com"))
        onView(withId(R.id.input_bio_edit_text))
            .perform(scrollTo(), typeText("some random text."))

        onView(withId(R.id.input_submit_btn)).perform(scrollTo(), click())
        onView(withId(R.id.input_error_text_view))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        assertEquals(navController.currentDestination?.id, R.id.detailFragment)
    }

//    @Test
//    fun onSubmit_invalidEmail_showEditTextError() {
//        onView(withId(R.id.input_email_edit_text))
//            .perform(typeText("vishal123456@somemail"))
//        onView(withId(R.id.input_submit_btn))
//            .perform(scrollTo(), click())
//        onView(withId(R.id.input_email_edit_text))
//            .check(matches(hasErrorText(`is`("Enter valid email address!"))))
//        onView(withId(R.id.input_error_text_view))
//            .check(matches(isDisplayed()))
//    }

    @After
    fun tearDown() {
    }
}