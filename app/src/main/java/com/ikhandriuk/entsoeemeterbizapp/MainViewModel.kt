package com.ikhandriuk.entsoeemeterbizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhandriuk.entsoeemeterbizapp.model.AuthorizationItem
import com.ikhandriuk.entsoeemeterbizapp.model.LogOutItem
import com.ikhandriuk.entsoeemeterbizapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myAuthResponse: MutableLiveData<Response<AuthorizationItem>> = MutableLiveData()

    fun setAuthorization(login: String, pass: String) {
        viewModelScope.launch {
            val response = repository.setAuthorization(login, pass)
            myAuthResponse.value = response
        }
    }

    val logOutResponse: MutableLiveData<Response<LogOutItem>> = MutableLiveData()

    fun logOut(code: String) {
        viewModelScope.launch {
            val response = repository.logOut(code)
            logOutResponse.value = response
        }
    }

}