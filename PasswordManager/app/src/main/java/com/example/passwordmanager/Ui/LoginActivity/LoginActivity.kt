package com.example.passwordmanager.Ui.LoginActivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.passwordmanager.R

class LoginActivity : AppCompatActivity() {

    //Ya estan referenciados los componentes de la UI son estos:
    private lateinit var buttonLogin: Button
    private lateinit var editTextNameUser: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin = findViewById<Button>(R.id.buttonLogin)
        editTextNameUser = findViewById(R.id.editTextNameUser)
        editTextPassword = findViewById(R.id.editTextPassword)

        //SE HABLA A CLASE
        val Fingerprint = FingerPrint()

        buttonLogin.setOnClickListener(){
            if(editTextNameUser.text.toString() == "Lucio" && editTextPassword.text.toString() == "1234"){
                Fingerprint.checkDevice(this)
            }else{
                //si los datos no son correctos
                Toast.makeText(this, "Credendiales incorrectas",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}