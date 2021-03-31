package com.vishalgaur.testinguserdata

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class InputFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInContainer<InputFragment>(themeResId = R.style.Theme_TestingUserData)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.vishalgaur.testinguserdata", appContext.packageName)
    }

    @Test
    fun userCanEnterName() {
        Espresso.onView(withId(R.id.input_name_edit_text))
            .perform(typeText("Vishal Gaur"))
    }

    @Test
    fun userCanEnterEmail() {
        Espresso.onView(withId(R.id.input_email_edit_text))
            .perform(typeText("vishal123456@somemail.com"))
    }

    @Test
    fun userCanEnterMobile() {
        Espresso.onView(withId(R.id.input_mobile_edit_text))
            .perform(typeText("8989895656"))
    }

    @Test
    fun userCanEnterBio() {
        Espresso.onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random bio with multiple      spaces."))
    }

    @Test
    fun userCanEnterBioWithMultipleLines() {
        Espresso.onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random bio with enter \nsome other text.\nsome other text again!"))
    }

    @Test
    fun showsErrorOnSubmitIfFormNotFilled() {
        Espresso.onView(withId(R.id.input_submit_btn)).perform(click())
        Espresso.onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showsErrorOnSubmitIfFormNotFilledCompletely() {
        Espresso.onView(withId(R.id.input_mobile_edit_text))
            .perform(typeText("54687954"))
        Espresso.onView(withId(R.id.input_bio_edit_text))
            .perform(typeText("some random bio with multiple      spaces."))

        Espresso.onView(withId(R.id.input_submit_btn)).perform(scrollTo(), click())
        Espresso.onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

//    @Test
//    fun showsErrorOnSubmitWithInvalidEmail() {
//        Espresso.onView(withId(R.id.input_email_edit_text))
//            .perform(typeText("vishal123456@somemail"))
//        Espresso.onView(withId(R.id.input_submit_btn))
//            .perform(scrollTo(), click())
//        Espresso.onView(withId(R.id.input_email_edit_text))
//            .check(ViewAssertions.matches())
//        Espresso.onView(withId(R.id.input_error_text_view))
//            .check(ViewAssertions.matches(isDisplayed()))
//    }

    @After
    fun tearDown() {
    }
}