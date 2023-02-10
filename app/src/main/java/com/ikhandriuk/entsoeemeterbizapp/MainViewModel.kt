package com.ikhandriuk.entsoeemeterbizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhandriuk.entsoeemeterbizapp.Repository.Repository
import com.ikhandriuk.multiplescreensapp.Model.AuthorizationItem
import com.ikhandriuk.multiplescreensapp.Model.LogOutItem
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
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

    val myDataResponse: MutableLiveData<Response<ParametersItem>> = MutableLiveData()
    fun getDatta(
        code: String,
        notlast: String,
        action: String,
        date: String,
        ids: String,
        time: String
    ) {
        viewModelScope.launch {
            val response = repository.getDatta(code, notlast, action, date, ids, time)
            myDataResponse.value = response
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