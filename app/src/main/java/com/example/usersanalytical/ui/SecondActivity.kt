package com.example.usersanalytical.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.usersanalytical.databinding.ActivitySecondBinding
import com.example.usersanalytical.ui.app.App
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    private lateinit var secondViewModel: SecondViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(!intent.hasExtra(EXTRA_NAME)) {
            finish()
            return
        }
        val name = intent.getStringExtra(EXTRA_NAME)!!
        val password = intent.getStringExtra(EXTRA_PASSWORD)!!

        secondViewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]
        secondViewModel.getUser(name, password = password)
        secondViewModel.user.observe(this){
            with(binding){
                textViewName.text = "Клиент: ${it.name}"
                textViewAge.text = "возраст: ${it.year_old}"
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
        }
    }

    companion object{
        private const val EXTRA_NAME = "name"
        private const val EXTRA_PASSWORD = "password"

        fun newIntent(context: Context, name:String, password: String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_NAME,name)
            intent.putExtra(EXTRA_PASSWORD,password)
            return intent
        }
    }
}