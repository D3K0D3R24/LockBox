package com.example.passwordmanager.Ui.AddPasswordActivity

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.passwordmanager.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddPasswordActivity : AppCompatActivity(),
    ClassBottomSheetGeneratePassword.OnPasswordGeneratedListener {

    private lateinit var editTextPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_password)

        val buttomGeneratePassword = findViewById<ImageButton>(R.id.buttomGeneratePassword)
        editTextPassword = findViewById(R.id.editTextPassword)

        buttomGeneratePassword.setOnClickListener {

            val bottomSheetFragment = ClassBottomSheetGeneratePassword(this)
            bottomSheetFragment.setOnPasswordGeneratedListener(this)

            bottomSheetFragment.show(supportFragmentManager,"Buttom")
        }
    }

    override fun onPasswordGenerated(password: String) {
        editTextPassword.setText(password)
    }
}