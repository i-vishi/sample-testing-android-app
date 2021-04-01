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
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
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
        insertInNameEditText("Vishal Gaur  ")
    }

    @Test
    fun userCanEnterEmail() {
        insertInEmailEditText("vishal1236@somemail.com")
    }

    @Test
    fun userCanEnterMobile() {
        insertInMobileEditText("8989895656")
    }

    @Test
    fun userCanEnterBio() {
        insertInBioEditText("some random bio with multiple      spaces.")
    }

    @Test
    fun userCanEnterBioWithMultipleLines() {
        insertInBioEditText("   some random bio with enter     \nsome other text.\n   some other text again!")
    }

    @Test
    fun onSubmit_emptyForm_showError() {
        clickSubmitButton()

        onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onSubmit_partiallyFilledForm_showError() {
        insertInMobileEditText("54687954")
        insertInBioEditText("some random text.")

        clickSubmitButton()

        onView(withId(R.id.input_error_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onSubmit_completeAndValidForm_noErrorNavigateToDetailScreen() {
        insertInNameEditText("Vishal Gaur")
        insertInMobileEditText("8468778954")
        insertInEmailEditText("vishal123456@somemail.com")
        insertInBioEditText("some random text.")

        clickSubmitButton()

        onView(withId(R.id.input_error_text_view))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        assertEquals(navController.currentDestination?.id, R.id.detailFragment)
    }

    @Test
    fun onSubmit_invalidEmailValidMob_showEmailError() {
        insertInNameEditText("Vishal Gaur")
        insertInMobileEditText("8468778954")
        insertInEmailEditText("vish56@somemailcom")
        insertInBioEditText("some random text.")

        clickSubmitButton()

        onView(withId(R.id.input_email_edit_text))
            .check(matches(hasErrorText(`is`("Enter valid email address!"))))
        onView(withId(R.id.input_mobile_edit_text))
            .check(matches(not(hasErrorText(`is`("")))))
        onView(withId(R.id.input_error_text_view))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        assertEquals(navController.currentDestination?.id, R.id.inputFragment)
    }

    @Test
    fun onSubmit_validEmailInvalidMob_showMobError() {
        insertInNameEditText("Vishal Gaur")
        insertInMobileEditText("84687754")
        insertInEmailEditText("vishal123456@somemail.com")
        insertInBioEditText("some random text.")

        clickSubmitButton()

        onView(withId(R.id.input_email_edit_text))
            .check(matches(not(hasErrorText(`is`("")))))
        onView(withId(R.id.input_mobile_edit_text))
            .check(matches(hasErrorText(`is`("Enter valid mobile number!"))))
        onView(withId(R.id.input_error_text_view))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        assertEquals(navController.currentDestination?.id, R.id.inputFragment)
    }

    @Test
    fun onSubmit_invalidEmailInvalidMob_showBothError() {
        insertInNameEditText("Vishal Gaur")
        insertInMobileEditText("1008468778")
        insertInEmailEditText("vishal@somemailcom")
        insertInBioEditText("some random text.")

        clickSubmitButton()

        onView(withId(R.id.input_email_edit_text))
            .check(matches(hasErrorText(`is`("Enter valid email address!"))))
        onView(withId(R.id.input_mobile_edit_text))
            .check(matches(hasErrorText(`is`("Enter valid mobile number!"))))
        onView(withId(R.id.input_error_text_view))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        assertEquals(navController.currentDestination?.id, R.id.inputFragment)
    }

    @After
    fun tearDown() {
    }


    private fun insertInNameEditText(name: String) =
        onView(withId(R.id.input_name_edit_text)).perform(scrollTo(), typeText(name))

    private fun insertInEmailEditText(email: String) =
        onView(withId(R.id.input_email_edit_text)).perform(scrollTo(), typeText(email))

    private fun insertInMobileEditText(mob: String) =
        onView(withId(R.id.input_mobile_edit_text)).perform(scrollTo(), typeText(mob))

    private fun insertInBioEditText(bio: String) =
        onView(withId(R.id.input_bio_edit_text)).perform(scrollTo(), typeText(bio))

    private fun clickSubmitButton() =
        onView(withId(R.id.input_submit_btn)).perform(scrollTo(), click())

}