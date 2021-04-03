package com.vishalgaur.testinguserdata

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class DetailActivityTest {

	@get:Rule
	var detailActivityScenarioRule = activityScenarioRule<DetailActivity>()

	private lateinit var scenario: ActivityScenario<DetailActivity>

	@Test
	fun infoIsSet() {

		val name = "Vishal"
		val email = "vishal@mail.com"
		val mob = "7056897878"
		val bio = "iweurhfgw roirhf \neogiuehri  oisfe."

		val argBundle = bundleOf(
				"userName" to name,
				"userEmail" to email,
				"userMob" to mob,
				"userBio" to bio
		)
		val detailIntent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).putExtras(argBundle)
		scenario = ActivityScenario.launch(detailIntent)

		onView(withId(R.id.detail_name)).check(matches(withText("Vishal")))
		onView(withId(R.id.detail_email)).check(matches(withText("vishal@mail.com")))
		onView(withId(R.id.detail_mobile)).check(matches(withText("7056897878")))
		onView(withId(R.id.detail_bio)).check(matches(withText("iweurhfgw roirhf \neogiuehri  oisfe.")))
	}

	@After
	fun cleanup() {
		scenario.close()
	}
}