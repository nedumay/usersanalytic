package com.example.usersanalytical.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersanalytical.domain.model.Users
import com.example.usersanalytical.domain.usecase.GetUserFromDbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUserFromDbUseCase: GetUserFromDbUseCase
) : ViewModel() {

    private val _userLogin = MutableLiveData<Users>()
    val userLogin: LiveData<Users>
        get() = _userLogin

    private val _errorInputNameHaveBd = MutableLiveData<Boolean>()
    val errorInputNameHaveBd: LiveData<Boolean>
        get() = _errorInputNameHaveBd

    fun getUser(name: String){
        viewModelScope.launch {
            val userDb = getUserFromDbUseCase.invoke(name = name)
            Log.d("getUser","UserDb: $userDb")
            if(userDb.name  != ""){
                _userLogin.value = userDb
                Log.d("getUser","User: ${_userLogin.value}")
                _errorInputNameHaveBd.value = false
                Log.d("getUser","Error LD false ${_errorInputNameHaveBd.value}")
            } else{
                _errorInputNameHaveBd.value = true
                Log.d("getUser","Error LD true ${_errorInputNameHaveBd.value}")
            }
        }
    }
}