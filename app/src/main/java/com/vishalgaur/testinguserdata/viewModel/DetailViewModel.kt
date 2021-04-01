package com.vishalgaur.testinguserdata.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "DetailViewModel"

class DetailViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    private val _userPhone = MutableLiveData<String>()
    val userPhone: LiveData<String> get() = _userPhone

    private val _userBio = MutableLiveData<String>()
    val userBio: LiveData<String> get() = _userBio

    fun submitData(name: String, email: String, phone: String, bio: String): String {
        var err = "ERROR"
        if (!isEmailValid(email)) {
            err += "_EMAIL"
        }
        if (!isPhoneValid(phone)) {
            err += "_PHONE"
        }
        return if (err === "ERROR") {
            _userName.value = name.trim()
            _userEmail.value = email.trim()
            _userPhone.value = phone.trim()
            _userBio.value = bio.trim()
            "NO_$err"
        } else err
    }
}