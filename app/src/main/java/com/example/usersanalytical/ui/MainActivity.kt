package com.example.usersanalytical.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextName.text?.trim().toString()
            mainViewModel.getUser(name)
            mainViewModel.errorInputNameHaveBd.observe(this){error ->
                errorNameHaveDb = error
                if(errorNameHaveDb != true){
                    val intent = SecondActivity.newIntent(this, name)
                    startActivity(intent)
                } else{
                    Toast.makeText(this@MainActivity, TOAST_MESSAGE, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    companion object {
        private const val TOAST_MESSAGE = "Account does not exist"
    }
}