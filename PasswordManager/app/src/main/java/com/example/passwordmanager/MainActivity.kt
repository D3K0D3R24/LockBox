package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.passwordmanager.GenerarPass.GenerarPass
import com.example.passwordmanager.Ui.AddPasswordActivity.AddPasswordActivity
import com.example.passwordmanager.Ui.LoginActivity.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Codigo que se uso para probar el activity del login*/
        val intent = Intent(this@MainActivity, AddPasswordActivity::class.java)
        startActivity(intent)
    }
}