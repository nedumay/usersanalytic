package com.example.usersanalytical.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersanalytical.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class SecondViewModel : ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dataBase = FirebaseDatabase.getInstance(
        "https://usersanalytical-default-rtdb.europe-west1.firebasedatabase.app/"
    )
    private val usersRef: DatabaseReference = dataBase.reference

    private var _firebaseUser = MutableLiveData<FirebaseUser>()
    val firebaseUser: LiveData<FirebaseUser>
        get() = _firebaseUser

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        auth.addAuthStateListener(FirebaseAuth.AuthStateListener { firebaseAuth ->
            _firebaseUser.value = firebaseAuth.currentUser
        })
    }

    fun loadData(userId: String){

        usersRef.child("User").child(userId).get().addOnSuccessListener {
            val data : User? = it.getValue(User::class.java)
            _user.value = data
        }.addOnFailureListener{
            Log.d("UserId", "${it}")
        }

    }

    fun logout() {
        auth.signOut()
    }

}