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

        secondViewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]
        secondViewModel.getUser(name)
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

    }

    companion object{
        private const val EXTRA_NAME = "name"

        fun newIntent(context: Context, name:String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_NAME,name)
            return intent
        }
    }
}