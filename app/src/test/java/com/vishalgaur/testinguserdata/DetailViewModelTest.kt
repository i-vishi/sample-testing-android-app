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
import java.util.regex.Pattern.matches

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
}