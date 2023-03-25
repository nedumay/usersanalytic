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
import com.example.usersanalytical.ui.app.App
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        var errorNameHaveDb: Boolean

        isNameValid()
        isPasswordValid()

        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextName.text?.trim().toString()
            val password = binding.editTextPassword.text?.trim().toString()
            mainViewModel.getUser(name, password)
            mainViewModel.errorInputNameHaveBd.observe(this){error ->
                errorNameHaveDb = error
                if(errorNameHaveDb != true){
                    val intent = SecondActivity.newIntent(this, name, password)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this@MainActivity, TOAST_MESSAGE, Toast.LENGTH_LONG).show()
                }
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