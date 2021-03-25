package com.vishalgaur.testinguserdata.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    private val _userPhone = MutableLiveData<String>()
    val userPhone: LiveData<String> get() = _userPhone

    private val _userBio = MutableLiveData<String>()
    val userBio: LiveData<String> get() = _userBio

    fun submitData(name: String, email: String, mob: String, bio: String) {
        _userName.value = name
        _userEmail.value = email
        _userPhone.value = mob
        _userBio.value = bio
    }

}