package com.example.passwordmanager.Ui.AddPasswordActivity

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.BdPassword.Contrasenas
import com.example.passwordmanager.BdPassword.ContraseñasCrud
import com.example.passwordmanager.BdPassword.MyDatabaseHelper
import com.example.passwordmanager.Data.RecyclerViewServices.AdapterRecyclerViewServices
import com.example.passwordmanager.Data.RecyclerViewServices.dataClassServices
import com.example.passwordmanager.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddPasswordActivity : AppCompatActivity(),
    ClassBottomSheetGeneratePassword.OnPasswordGeneratedListener {
    private lateinit var imageButtonIconService: ImageButton
    private lateinit var editTextPassword: EditText
    private lateinit var buttonAceptar: Button
    private lateinit var editTextTitle: EditText
    private lateinit var editTextURL: EditText
    private lateinit var editTextUser: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_password)
        imageButtonIconService = findViewById(R.id.imageButtonIconService)
        buttonAceptar = findViewById(R.id.buttonAceptar)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextURL = findViewById(R.id.editTextURL)
        editTextUser = findViewById(R.id.editTextUser)

        val buttomGeneratePassword = findViewById<ImageButton>(R.id.buttomGeneratePassword)
        editTextPassword = findViewById(R.id.editTextPassword)

        buttomGeneratePassword.setOnClickListener {

            val bottomSheetFragment = ClassBottomSheetGeneratePassword(this)
            bottomSheetFragment.setOnPasswordGeneratedListener(this)

            bottomSheetFragment.show(supportFragmentManager,"Buttom")
        }

        buttonAceptar.setOnClickListener {
            val myDataClassDao = ContraseñasCrud(MyDatabaseHelper(this))
            val liveData = myDataClassDao.insertOne(
                Contrasenas(0, ByteArray(0),editTextTitle.text.toString(),editTextURL.text.toString(),editTextUser.text.toString(),editTextPassword.text.toString(),"nota")
            )
        }

        imageButtonIconService.setOnClickListener {
            createCustomDialog(this@AddPasswordActivity)
        }

    }

    private fun createCustomDialog(context: Context):Dialog {

        // También puedes declarar una lista de servicios
        val servicios: List<dataClassServices> = listOf(
            dataClassServices(R.drawable.logo_wifi_48, "Wifi"),
            dataClassServices(R.drawable.logo_facebook_48, "Facebook"),
            dataClassServices(R.drawable.logo_amazon_48, "Amazon"),
            dataClassServices(R.drawable.logo_dribbble_48, "Dribble"),
            dataClassServices(R.drawable.logo_google, "Google"),
            dataClassServices(R.drawable.logo_gmail_48, "Gmail"),
            dataClassServices(R.drawable.logo_microsoft_48, "Microsoft"),
            dataClassServices(R.drawable.logo_pinterest, "Pinterest"),
            dataClassServices(R.drawable.logo_tik_tok_48, "Tik Tok")
        )
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_services_plataforms_layout)

        val recyclerView = dialog.findViewById<RecyclerView>(R.id.recyclerViewItemsServices)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterRecyclerViewServices(servicios)
        return dialog
    }

    override fun onPasswordGenerated(password: String) {
        editTextPassword.setText(password)
    }
}