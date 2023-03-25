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

class SecondViewModel @Inject constructor(
    private val getUserFromDbUseCase: GetUserFromDbUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Users>()
    val user: LiveData<Users>
        get() = _user

    private val _errorNameHaveBd = MutableLiveData<Boolean>()
    val errorNameHaveBd: LiveData<Boolean>
        get() = _errorNameHaveBd

    fun getUser(name: String, password: String){
        viewModelScope.launch {
            val userDb = getUserFromDbUseCase.invoke(name = name, password = password)
            Log.d("getUser","UserDb: $userDb")
            if(userDb.name  != "" && userDb.password != ""){
                _user.value = userDb
                Log.d("getUser","User: ${_user.value}")
                _errorNameHaveBd.value = false
                Log.d("getUser","Error LD false ${_errorNameHaveBd.value}")
            } else{
                _errorNameHaveBd.value = true
                Log.d("getUser","Error LD true ${_errorNameHaveBd.value}")
            }
        }
    }

}