package com.darleyleal.motivation.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.darleyleal.motivation.Infrastructure.MotivationConstants
import com.darleyleal.motivation.R
import com.darleyleal.motivation.Infrastructure.SecurityPreferences
import com.darleyleal.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        verifySDKVersion()
        verifyUserName()

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editNameUser.text.toString()
        if (name.isNotEmpty()) {
            SecurityPreferences(this).putValue(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getValue(MotivationConstants.KEY.USER_NAME)
        if (name.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun verifySDKVersion() {
        if (Build.VERSION.SDK_INT >= 23) {
            val window: Window = window
            window.statusBarColor = Color.parseColor("#5F1049")
            window.navigationBarColor = Color.parseColor("#5F1049")
        }
    }
}