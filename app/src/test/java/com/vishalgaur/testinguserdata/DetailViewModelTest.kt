package com.vishalgaur.testinguserdata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vishalgaur.testinguserdata.viewModel.DetailViewModel
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpViewModel() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun submitData_allValid_returnsNoError() {
        val name = "Vishal"
        val email = "   vishal@mail.com "
        val mob = "  7056897878"
        val bio = "iweurhfgw roirhf \neogiuehri  oisfe."

        val result = detailViewModel.submitData(name, email, mob, bio)
        val nameValue = detailViewModel.userName.getOrAwaitValue()
        val emailValue = detailViewModel.userEmail.getOrAwaitValue()
        val mobValue = detailViewModel.userPhone.getOrAwaitValue()
        val bioValue = detailViewModel.userBio.getOrAwaitValue()

        assertThat(result, `is`("NO_ERROR"))
        assertThat(nameValue, `is`("Vishal"))
        assertThat(emailValue, `is`("vishal@mail.com"))
        assertThat(mobValue, `is`("7056897878"))
        assertThat(bioValue, `is`("iweurhfgw roirhf \neogiuehri  oisfe."))
    }

    @Test
    fun submitData_invalidEmail_returnsEmailError() {
        val name = "Vishal"
        val email = "   vishal@mailcom "
        val mob = "  7056897878"
        val bio = "iweurhfgw roirhf \neogiuehri  oisfe."

        val result = detailViewModel.submitData(name, email, mob, bio)

        assertThat(result, `is`("ERROR_EMAIL"))
        assertThat(detailViewModel.userName.value, `is`(nullValue()))
        assertThat(detailViewModel.userEmail.value, `is`(nullValue()))
        assertThat(detailViewModel.userPhone.value, `is`(nullValue()))
        assertThat(detailViewModel.userBio.value, `is`(nullValue()))
    }

    @Test
    fun submitData_invalidMobile_returnsMobileError() {
        val name = "Vishal"
        val email = "   vishal@mail.com "
        val mob = "  705689787   "
        val bio = "     iweurhfgw roirhf \neogiuehri  oisfe       ."

        val result = detailViewModel.submitData(name, email, mob, bio)

        assertThat(result, `is`("ERROR_PHONE"))
        assertThat(detailViewModel.userName.value, `is`(nullValue()))
        assertThat(detailViewModel.userEmail.value, `is`(nullValue()))
        assertThat(detailViewModel.userPhone.value, `is`(nullValue()))
        assertThat(detailViewModel.userBio.value, `is`(nullValue()))
    }

    @Test
    fun submitData_invalidEmailAndMobile_returnsBothError() {
        val name = "Vishal"
        val email = "   vishalmail.com "
        val mob = "  705689787   "
        val bio = "     iweurhfgw roirhf \neogiuehri  oisfe       ."

        val result = detailViewModel.submitData(name, email, mob, bio)

        assertThat(result, `is`("ERROR_EMAIL_PHONE"))
        assertThat(detailViewModel.userName.value, `is`(nullValue()))
        assertThat(detailViewModel.userEmail.value, `is`(nullValue()))
        assertThat(detailViewModel.userPhone.value, `is`(nullValue()))
        assertThat(detailViewModel.userBio.value, `is`(nullValue()))
    }
}