package com.example.usersanalytical.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser

class MainViewModel : ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var _firebaseUser = MutableLiveData<FirebaseUser>()
    val firebaseUser: LiveData<FirebaseUser>
        get() = _firebaseUser

    init {
        auth.addAuthStateListener(AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                _firebaseUser.value = firebaseAuth.currentUser
            }
        })
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            //_firebaseUser.value  = it.user
        }.addOnFailureListener { e ->
            _error.value = e.message
        }
    }

}