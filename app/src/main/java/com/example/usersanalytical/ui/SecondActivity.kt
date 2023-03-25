package com.example.usersanalytical.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.usersanalytical.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private lateinit var secondViewModel: SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentUserId = intent.getStringExtra(EXTRA_CURRENT_USER_ID)
        Log.d("UserId", "$currentUserId")
        secondViewModel = ViewModelProvider(this)[SecondViewModel::class.java]
        secondViewModel.loadData(currentUserId!!)
        secondViewModel.firebaseUser.observe(this){
            Log.d("UserId", "$it")
            if (it == null) {
                val intent = MainActivity.newIntent(this)
                startActivity(intent)
                finish()
            }
        }

        secondViewModel.user.observe(this){
            Log.d("UserId", "$it")
            with(binding){
                textViewName.text = "Клиент: ${it.name}"
                textViewAge.text = "возраст: ${it.age}"
                textViewHemoglobinVal.text = it.hemoglobin
                textViewErythrocytesVal.text = it.erythrocytes
                textViewPlateletsVal.text = it.platelets
                textViewLymphocytesVal.text = it.lymphocytes
                textViewMonocytesVal.text = it.monocytes
                textViewBasophilsVal.text = it.basophils
            }
        }

        binding.buttonExit.setOnClickListener {
            val intent = MainActivity.newIntent(this)
            startActivity(intent)
            finish()
            secondViewModel.logout()
        }

    }

    companion object{
        private const val EXTRA_CURRENT_USER_ID = "user_id"
        fun newIntent(context: Context, currentUserId: String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_CURRENT_USER_ID, currentUserId)
            return intent
        }
    }
}