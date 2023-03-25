package com.example.usersanalytical.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.usersanalytical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        observeView()

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextName.text?.trim().toString()
            val password = binding.editTextPassword.text?.trim().toString()
            mainViewModel.login(email, password)

        }
    }

    private fun observeView() {
        mainViewModel.error.observe(this) {
            if (it != null) {
                Toast.makeText(this@MainActivity, TOAST_MESSAGE, Toast.LENGTH_LONG).show()
            }
        }
        mainViewModel.firebaseUser.observe(this) {
            if (it != null) {
                val intent = SecondActivity.newIntent(this, it.uid)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun isNameValid() {
        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 0) {
                    binding.tilName.error = NOT_BE_EMPTY
                }
            }
        })
    }

    private fun isPasswordValid() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 0) {
                    binding.tilPassword.error = NOT_BE_EMPTY
                }
            }
        })
    }


    companion object {
        private const val TOAST_MESSAGE = "Account does not exist"
        private const val EMPTY_FIELD = ""
        private const val INVALID_ADDRESS = "Invalid login"
        private const val INVALID_PASSWORD = "Invalid password"
        private const val NOT_BE_EMPTY = "Required Field!"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}