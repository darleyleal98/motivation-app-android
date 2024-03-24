package com.darleyleal.motivation.UI

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.View.OnClickListener
import android.view.Window

import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.darleyleal.motivation.Data.Mock
import com.darleyleal.motivation.Infrastructure.MotivationConstants
import com.darleyleal.motivation.R
import com.darleyleal.motivation.Infrastructure.SecurityPreferences
import com.darleyleal.motivation.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity(), OnClickListener, Parcelable {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    // Implementação do Android Studio ->
    constructor(parcel: Parcel) : this() {
        categoryId = parcel.readInt()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleName()
        handleFilter(R.id.image_infinite)
        handleNextPhrase(categoryId)
        verifySDKVersion()

        // Eventos na tela
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageInfinite.setOnClickListener(this)
        binding.imageMood.setOnClickListener(this)
        binding.imageSun.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase(categoryId)
        } else if (view.id in listOf(R.id.image_infinite, R.id.image_mood, R.id.image_sun)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase(id: Int) {
        val phrase = Mock()
        binding.textMotivationPhrase.text = phrase.getPhrase(id).toString()
    }

    private fun handleFilter(id: Int) {
        // Mantém as cores dos ícones por padrão em preto
        binding.imageInfinite.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        binding.imageMood.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        binding.imageSun.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        // Quando um ícone for clicado o tom branco será atribuído
        when (id) {
            R.id.image_infinite -> {
                binding.imageInfinite.setColorFilter(
                    ContextCompat.getColor(
                        this, R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.ALL
            }

            R.id.image_mood -> {
                binding.imageMood.setColorFilter(
                    ContextCompat.getColor(
                        this, R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.MOOD
            }

            R.id.image_sun -> {
                binding.imageSun.setColorFilter(
                    ContextCompat.getColor(
                        this, R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.SUN
            }

            else -> {
                throw IllegalArgumentException("Opção inválida!")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleName() {
        val userName = SecurityPreferences(this).getValue(
            MotivationConstants.KEY.USER_NAME
        )
        binding.textApresentationName.text = "Olá, $userName"
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun verifySDKVersion() {
        if (Build.VERSION.SDK_INT >= 23) {
            val window: Window = window
            window.statusBarColor = Color.parseColor("#5F1049")
            window.navigationBarColor = Color.parseColor("#Ffffff")
        }
    }


    // Implementações do Android Studio ->
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}