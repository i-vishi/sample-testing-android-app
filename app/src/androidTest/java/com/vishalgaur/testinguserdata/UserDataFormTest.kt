package com.vishalgaur.testinguserdata

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserDataFormTest {

	@Before
	fun setup() {
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
		onView(withId(R.id.input_name_edit_text)).perform(typeText("Vishal Gaur"))
	}

	@Test
	fun userCanEnterEmail() {
		onView(withId(R.id.input_email_edit_text)).perform(typeText("vishal123456@somemail.com"))
	}

	@Test
	fun userCanEnterMobile() {
		onView(withId(R.id.input_mobile_edit_text)).perform(typeText("8989895656"))
	}

	@Test
	fun userCanEnterBio() {
		onView(withId(R.id.input_bio_edit_text))
				.perform(typeText("some random bio with multiple      spaces and  reicvb  ikejrbv."))
	}
}