package com.example.passwordmanager.GenerarPass

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.passwordmanager.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.security.SecureRandom

class GenerarPass : AppCompatActivity() {
    //Para la longitud de la contraseña el edtLongitud
    private lateinit var edtLongitud: EditText

    //Para seleccionar todas las opciones de mayus, minuscula y numeros
    private lateinit var opctodos: CheckBox
    private lateinit var checkmayus: CheckBox
    private lateinit var checkminus: CheckBox
    private lateinit var checknum: CheckBox

    /*Para incluir caracteres el checkcaracter*/
    private lateinit var checkcaracter: CheckBox

    /*Para Excluir caracteres el excheckcaracter*/
    private lateinit var excheckcaracter: CheckBox

    //boton para generar la contraseña
    private lateinit var btnGenerar: Button

    //textview donde se guardara la contraseña
    private lateinit var txtgenerarpass: TextView

    //Boton para copiar en portapapeles
    private lateinit var btnCopiar: Button

    //PAra agregar el caracter
    private lateinit var agregarcaracter: EditText

    //para eliminar un caracter en la lista
    private lateinit var removecaracter: EditText

    private lateinit var textview2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generar_pass)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            edtLongitud = findViewById(R.id.edtLongitud)
            opctodos = findViewById(R.id.opcTodo)
            checkmayus = findViewById(R.id.mayus)
            checkminus = findViewById(R.id.minus)
            checknum = findViewById(R.id.numeros)
            checkcaracter = findViewById(R.id.caracteres)
            excheckcaracter = findViewById(R.id.Excluircaracteres)
            btnGenerar = findViewById(R.id.btnGenerar)
            txtgenerarpass = findViewById(R.id.txtGeneratedPassword)
            btnCopiar = findViewById(R.id.btnCopiar)
            agregarcaracter = findViewById(R.id.agregarcaracter)
            removecaracter = findViewById(R.id.removecaracter)
            textview2 = findViewById(R.id.textview2)

            textview2.text = ("!@#$%^&*()_-+=<>?")

            btnGenerar.setOnClickListener {
                generatePassword()
            }
            opctodos.setOnClickListener {
                if (opctodos.isChecked) {
                    checkmayus.isChecked = true
                    checkminus.isChecked = true
                    checknum.isChecked = true
                    //  checkcaracter.isChecked = false
                    //  excheckcaracter.isChecked = false
                } else {
                    checkmayus.isChecked = false
                    checkminus.isChecked = false
                    checknum.isChecked = false
                    // checkcaracter.isChecked = false
                    // excheckcaracter.isChecked= false
                }
            }

            checkcaracter.setOnCheckedChangeListener { _, isChecked ->
                // Si está marcado, se activa la edición de caracteres personalizados
                agregarcaracter.visibility = if (isChecked) {
                    CheckBox.VISIBLE

                } else {
                    CheckBox.GONE
                }
            }

            excheckcaracter.setOnCheckedChangeListener { _, isChecked ->
                // Si está marcado, se activa la edición de caracteres personalizados
                removecaracter.visibility = if (isChecked) {
                    CheckBox.VISIBLE

                } else {
                    CheckBox.GONE
                }
            }
            btnCopiar.setOnClickListener {
                copiarAlPortapapeles(txtgenerarpass.text.toString())
            }
    }
    private fun generatePassword() {

        val longitud = edtLongitud.text.toString().toIntOrNull()

        if ((longitud == null || longitud < 1) || longitud > 60) {
            Toast.makeText(this, "Longitud no válida", Toast.LENGTH_SHORT).show()
            return
        } else {

            val casillasSeleccionadas = listOf(
                checkmayus,
                checkminus,
                checknum,
                checkcaracter,
                excheckcaracter
            ).any { it.isChecked }
            if (!casillasSeleccionadas) {
                Toast.makeText(this, "Seleccione al menos una casilla", Toast.LENGTH_SHORT)
                    .show()
                return
            } else {
                val incluirmayus = checkmayus.isChecked
                val incluirminus = checkminus.isChecked
                val incluirnum = checknum.isChecked

                val incluircaracter = checkcaracter.isChecked
                val excluircaracter = excheckcaracter.isChecked

                val password = generateRandomPassword(
                    longitud,
                    incluirmayus,
                    incluirminus,
                    incluirnum,
                    incluircaracter,
                    excluircaracter,
                    removecaracter.text.toString()
                )
                txtgenerarpass.text = "$password"
            }
        }
    }

    private fun generateRandomPassword(
        length: Int,
        includeUppercase: Boolean,
        includeLowercase: Boolean,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        excluircaracter: Boolean,
        exluircaracter: String

    ): String {
        val charset = StringBuilder()
        if (includeUppercase) charset.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        if (includeLowercase) charset.append("abcdefghijklmnopqrstuvwxyz")
        if (includeNumbers) charset.append("0123456789")
        //Utiliza este apartado para eliminar los caracteres en el editext
        if (excluircaracter) charset.append("!@#$%^&*()_-+=<>?")

        val customChars = agregarcaracter.text.toString()
        if (includeSpecialChars) {
            if (customChars.isNotEmpty()) {
                charset.append(customChars)
            } else {
                removecaracter.text.clear()
                charset.append("!@#$%^&*()_-+=<>?")
            }
        }

        val filteredCharset =
            StringBuilder(charset.toString()).filterNot { exluircaracter.contains(it) }

        val random = SecureRandom()
        val password = StringBuilder()
        repeat(length) {
            password.append(filteredCharset[random.nextInt(filteredCharset.length)])
        }

        return password.toString()
    }

    private fun copiarAlPortapapeles(contrasena: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("contrasena", contrasena)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(this, "Contraseña copiada al portapapeles", Toast.LENGTH_SHORT).show()
    }
}